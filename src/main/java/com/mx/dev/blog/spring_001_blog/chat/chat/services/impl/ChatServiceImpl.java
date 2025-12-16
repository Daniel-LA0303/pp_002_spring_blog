package com.mx.dev.blog.spring_001_blog.chat.chat.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.chat.chat.entities.ChatEntity;
import com.mx.dev.blog.spring_001_blog.chat.chat.repositories.ChatRepository;
import com.mx.dev.blog.spring_001_blog.chat.chat.services.ChatService;
import com.mx.dev.blog.spring_001_blog.chat.chat.utils.dto.ChatResponseDTO;
import com.mx.dev.blog.spring_001_blog.chat.chat.utils.mapper.ChatMapper;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageState;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class ChatServiceImpl implements ChatService {

	private final ChatRepository chatRepository;

	private final UserService userService;

	private final ChatMapper mapper;

	public ChatServiceImpl(ChatRepository chatRepository, UserService userService, ChatMapper mapper) {
		this.chatRepository = chatRepository;
		this.userService = userService;
		this.mapper = mapper;
	}

	@Override
	public ChatResponseDTO createChat(Long senderId, Long receiverId) throws ServiceException {
		// 1. Sort IDs for consistency
		Long minUserId = Math.min(senderId, receiverId);
		Long maxUserId = Math.max(senderId, receiverId);

		// 2. Check for existing chat
		Optional<ChatEntity> existingChat = chatRepository.findChatBySortedUsers(minUserId, maxUserId);
		ChatEntity chat;

		// if exists chat return
		if (existingChat.isPresent()) {
			chat = existingChat.get();
		} else {
			// 3. Create new chat if does not exists
			UserEntity sender = userService.getOneUserOrThrow(minUserId);
			UserEntity receiver = userService.getOneUserOrThrow(maxUserId);

			chat = new ChatEntity();
			chat.setSender(sender);
			chat.setRecipient(receiver);
			chat = chatRepository.save(chat);
		}

		// 4. Validate ID generation
		if (chat.getChatId() == null) {
			throw new RuntimeException("Chat ID is null after save");
		}

		// 5. Build response DTO
		ChatResponseDTO response = new ChatResponseDTO();
		response.setId(chat.getChatId());

		// 6. Set other participant's name
		response.setName(chat.getTargetChatName(receiverId));

		response.setLastMessage(chat.getLastMessage());
		response.setLastMessageTime(chat.getLastMessageTime());

		// 7. Check other user's online status
		UserEntity otherUser = senderId.equals(chat.getSender().getUserId()) ? chat.getRecipient() : chat.getSender();
		boolean isOnline = otherUser.getLastSeen() != null
				&& otherUser.getLastSeen().isAfter(LocalDateTime.now().minusMinutes(5));
		response.setRecipientOnline(isOnline);

		// 8. Count unread messages
		Long unreadCount = 0L;
		if (chat.getMessages() != null) {
			unreadCount = chat.getMessages().stream()
					.filter(msg -> msg.getState() == MessageState.SENT && msg.getReceiverId().equals(senderId)).count();
		}
		response.setUnreadCount(unreadCount);

		// 9. Set participant IDs
		response.setSenderId(chat.getSender().getUserId());
		response.setReceiverId(chat.getRecipient().getUserId());

		return response;
	}

	@Override
	public Long getAuthenticatedUserId(Authentication authentication) throws ServiceException {
		String email = authentication.getName(); // because you log in with email

		UserEntity user = userService.getOneUserByEmailOrThrow(email);

		return user.getUserId();
	}

	@Override
	public ChatResponseDTO getChatById(String chatId, Long currentUserId) {
		// 1. Fetch chat entity
		ChatEntity chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));

		ChatResponseDTO response = new ChatResponseDTO();
		response.setId(chat.getChatId());

		// 2. Get other participant's name
		Long otherUserId = currentUserId.equals(chat.getSender().getUserId()) ? chat.getRecipient().getUserId()
				: chat.getSender().getUserId();
		response.setName(chat.getTargetChatName(otherUserId));

		// 3. Count unread messages
		Long unreadCount = 0L;
		if (chat.getMessages() != null) {
			unreadCount = chat.getMessages().stream()
					.filter(msg -> msg.getState() == MessageState.SENT && msg.getReceiverId().equals(currentUserId))
					.count();
		}
		response.setUnreadCount(unreadCount);

		// 4. Set last message info
		response.setLastMessage(chat.getLastMessage());
		response.setLastMessageTime(chat.getLastMessageTime());

		// 5. Check if other user is online
		UserEntity otherUser = currentUserId.equals(chat.getSender().getUserId()) ? chat.getRecipient()
				: chat.getSender();
		boolean isOnline = otherUser.getLastSeen() != null
				&& otherUser.getLastSeen().isAfter(LocalDateTime.now().minusMinutes(5));
		response.setRecipientOnline(isOnline);

		// 6. Set participant IDs
		response.setSenderId(chat.getSender().getUserId());
		response.setReceiverId(chat.getRecipient().getUserId());

		return response;
	}

	// get chat by user
	@Override
	public List<ChatResponseDTO> getChatsByReceiverId(Authentication currentUser) throws ServiceException {

		Long currentUserId = getAuthenticatedUserId(currentUser);

		return chatRepository.findChatsByUserId(currentUserId).stream()
				.map(chat -> mapper.toChatResponse(chat, currentUserId)).toList();
	}
}
