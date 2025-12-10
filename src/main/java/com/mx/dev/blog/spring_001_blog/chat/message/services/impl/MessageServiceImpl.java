package com.mx.dev.blog.spring_001_blog.chat.message.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.Authentication;
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
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public class MessageServiceImpl implements MessageService {

	private final UserService userService;

	private final ChatRepository chatRepository;

	private final MessageRepository messageRepository;

	private final NotificationMessageService notificationMessageService;

	private final FileService fileService;

	public MessageServiceImpl(UserService userService, ChatRepository chatRepository,
			MessageRepository messageRepository, NotificationMessageService notificationMessageService,
			FileService fileService) {
		this.userService = userService;
		this.chatRepository = chatRepository;
		this.messageRepository = messageRepository;
		this.notificationMessageService = notificationMessageService;
		this.fileService = fileService;
	}

	@Override
	public List<MessageResponseDTO> findChatMessages(String chatId) {
		// TODO Auto-generated method stub
		return null;
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
		// find chat by id
		ChatEntity chat = chatRepository.findById(messageRequest.getChatId())
				.orElseThrow(() -> new EntityNotFoundException("Chat not found"));

		// create body chat entity
		MessageEntity message = new MessageEntity();
		message.setContent(messageRequest.getContent());
		message.setChat(chat);
		message.setSenderId(messageRequest.getSenderId());
		message.setReceiverId(messageRequest.getReceiverId());
		message.setType(messageRequest.getType());
		message.setState(MessageState.SENT);

		// save message
		messageRepository.save(message);

		// create body notification entity
		NotificationMessageDTO notification = new NotificationMessageDTO();
		notification.setChatId(chat.getChatId());
		notification.setMessageType(messageRequest.getType());
		notification.setContent(messageRequest.getContent());
		notification.setSenderId(messageRequest.getSenderId());
		notification.setReceiverId(messageRequest.getReceiverId());
		notification.setType(NotificationMessageType.MESSAGE);
		notification.setChatName(chat.getTargetChatName(messageRequest.getSenderId()));

		// send notification via web socket to sender
		notificationMessageService.sendNotification(messageRequest.getSenderId(), notification);

		// send notification via web socket to receiver
		notificationMessageService.sendNotification(messageRequest.getReceiverId(), notification);

	}

	@Override
	public void setMessagesToSeen(String chatId, Authentication authentication) throws ServiceException {
		// find chat by id
		ChatEntity chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));

		// get id of sender and receiver
		Long recipientId = getRecipientId(chat, authentication);
		Long senderId = getSenderId(chat, authentication);

		// set message to seen or readed
		messageRepository.setMessagesToSeenByChatId(chatId, MessageState.SEEN);

		// create a notification
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
		// find chat by id
		ChatEntity chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));

		// get id of sender and reciver
		Long recipientId = getRecipientId(chat, authentication);
		Long senderId = getSenderId(chat, authentication);

		// save file in sever
		final String filePath = fileService.saveFile(file, senderId);

		// create body message
		MessageEntity message = new MessageEntity();
		message.setReceiverId(recipientId);
		message.setSenderId(senderId);
		message.setState(MessageState.SENT);
		message.setType(MessageType.IMAGE);
		message.setMedia(filePath);
		message.setChat(chat);

		// save message with file uploaded
		messageRepository.save(message);

		// create a notification
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
