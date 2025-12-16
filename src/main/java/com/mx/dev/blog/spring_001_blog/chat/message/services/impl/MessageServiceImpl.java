package com.mx.dev.blog.spring_001_blog.chat.message.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.chat.chat.entities.ChatEntity;
import com.mx.dev.blog.spring_001_blog.chat.chat.repositories.ChatRepository;
import com.mx.dev.blog.spring_001_blog.chat.config.file.FileService;
import com.mx.dev.blog.spring_001_blog.chat.config.file.FileUtils;
import com.mx.dev.blog.spring_001_blog.chat.message.entities.MessageEntity;
import com.mx.dev.blog.spring_001_blog.chat.message.repositories.MessageRepository;
import com.mx.dev.blog.spring_001_blog.chat.message.services.MessageService;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.dto.MessageRequestDTO;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.dto.MessageResponseDTO;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageState;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageType;
import com.mx.dev.blog.spring_001_blog.chat.notificationmessage.services.NotificationMessageService;
import com.mx.dev.blog.spring_001_blog.chat.notificationmessage.utils.dto.NotificationMessageDTO;
import com.mx.dev.blog.spring_001_blog.chat.notificationmessage.utils.enums.NotificationMessageType;
import com.mx.dev.blog.spring_001_blog.chat.notificationmessage.utils.mappers.MessageMapper;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class MessageServiceImpl implements MessageService {

	private final UserService userService;

	private final ChatRepository chatRepository;

	private final MessageRepository messageRepository;

	private final NotificationMessageService notificationMessageService;

	private final FileService fileService;

	private final MessageMapper mapper;

	public MessageServiceImpl(UserService userService, ChatRepository chatRepository,
			MessageRepository messageRepository, NotificationMessageService notificationMessageService,
			FileService fileService, MessageMapper mapper) {
		this.userService = userService;
		this.chatRepository = chatRepository;
		this.messageRepository = messageRepository;
		this.notificationMessageService = notificationMessageService;
		this.fileService = fileService;
		this.mapper = mapper;
	}

	@Override
	public List<MessageResponseDTO> findChatMessages(String chatId) {
		return messageRepository.findMessagesByChatId(chatId).stream().map(mapper::toMessageResponse).toList();
	}

	@Override
	public Long getAuthenticatedUserId(Authentication authentication) throws ServiceException {
		String email = authentication.getName(); // because you log in with email

		UserEntity user = userService.getOneUserByEmailOrThrow(email);

		return user.getUserId();
	}

	@Override
	public Long getRecipientId(ChatEntity chat, Authentication authentication) throws ServiceException {
		Long currentUserId = getAuthenticatedUserId(authentication);

		if (chat.getSender().getUserId().equals(currentUserId)) {
			return chat.getRecipient().getUserId();
		}

		return chat.getSender().getUserId();
	}

	@Override
	public Long getSenderId(ChatEntity chat, Authentication authentication) throws ServiceException {
		return getAuthenticatedUserId(authentication);
	}

	@Override
	public void saveMessage(MessageRequestDTO messageRequest) {

		// 1. search chat
		ChatEntity chat = chatRepository.findById(messageRequest.getChatId())
				.orElseThrow(() -> new EntityNotFoundException("Chat not found"));

		Long senderId = messageRequest.getSenderId();

		// 2. get receiver
		Long receiverId;
		if (chat.getSender().getUserId().equals(senderId)) {
			receiverId = chat.getRecipient().getUserId();
		} else {
			receiverId = chat.getSender().getUserId();
		}

		if (receiverId == null || receiverId <= 0) {
			throw new IllegalStateException("Invalid receiverId calculated");
		}

		// 3. build message and save
		MessageEntity message = new MessageEntity();
		message.setContent(messageRequest.getContent());
		message.setChat(chat);
		message.setSenderId(senderId);
		message.setReceiverId(receiverId);
		message.setType(messageRequest.getType());
		message.setState(MessageState.SENT);

		messageRepository.save(message);

		// 4. build notification message
		NotificationMessageDTO notification = new NotificationMessageDTO();
		notification.setChatId(chat.getChatId());
		notification.setMessageType(messageRequest.getType());
		notification.setContent(messageRequest.getContent());
		notification.setSenderId(senderId);
		notification.setReceiverId(receiverId);
		notification.setType(NotificationMessageType.MESSAGE);
		notification.setChatName(chat.getTargetChatName(senderId));

		// send message to two users
		notificationMessageService.sendNotification(senderId, notification);
		notificationMessageService.sendNotification(receiverId, notification);
	}

	@Override
	public void setMessagesToSeen(String chatId, Authentication authentication) throws ServiceException {
		// 1. find chat by id
		ChatEntity chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));

		// 2. get id of sender and receiver
		Long recipientId = getRecipientId(chat, authentication);
		Long senderId = getSenderId(chat, authentication);

		// 3. set message to seen or readed
		messageRepository.setMessagesToSeenByChatId(chatId, MessageState.SEEN);

		// 4. create a notification
		NotificationMessageDTO notification = new NotificationMessageDTO();
		notification.setChatId(chat.getChatId());
		notification.setType(NotificationMessageType.SEEN);
		notification.setReceiverId(recipientId);
		notification.setSenderId(senderId);

		// send notification to sender
		notificationMessageService.sendNotification(senderId, notification);

		// send notification to receiver
		notificationMessageService.sendNotification(recipientId, notification);
	}

	@Override
	public void uploadMediaMessage(String chatId, MultipartFile file, Authentication authentication)
			throws ServiceException {
		// 1. find chat by id
		ChatEntity chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));

		// 2. get id of sender and recepient
		Long recipientId = getRecipientId(chat, authentication);
		Long senderId = getSenderId(chat, authentication);

		// 3. save file in sever
		final String filePath = fileService.saveFile(file, senderId);

		// 4. create body message
		MessageEntity message = new MessageEntity();
		message.setReceiverId(recipientId);
		message.setSenderId(senderId);
		message.setState(MessageState.SENT);
		message.setType(MessageType.IMAGE);
		message.setMedia(filePath);
		message.setChat(chat);

		// 5. save message with file uploaded
		messageRepository.save(message);

		// 6. create a notification
		NotificationMessageDTO notification = new NotificationMessageDTO();
		notification.setChatId(chat.getChatId());
		notification.setType(NotificationMessageType.IMAGE);
		notification.setSenderId(senderId);
		notification.setReceiverId(recipientId);
		notification.setMessageType(MessageType.IMAGE);
		notification.setMedia(FileUtils.readFileFromLocation(filePath));
		notification.setChatName(chat.getTargetChatName(senderId));

		// send notification to sender
		notificationMessageService.sendNotification(senderId, notification);

		// send notification to receiver
		notificationMessageService.sendNotification(recipientId, notification);

	}

}
