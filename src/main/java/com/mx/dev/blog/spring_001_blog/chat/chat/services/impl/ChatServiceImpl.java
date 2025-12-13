package com.mx.dev.blog.spring_001_blog.chat.chat.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.chat.chat.entities.ChatEntity;
import com.mx.dev.blog.spring_001_blog.chat.chat.repositories.ChatRepository;
import com.mx.dev.blog.spring_001_blog.chat.chat.services.ChatService;
import com.mx.dev.blog.spring_001_blog.chat.chat.utils.dto.ChatResponseDTO;
import com.mx.dev.blog.spring_001_blog.chat.chat.utils.mapper.ChatMapper;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.repositories.UserRepository;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class ChatServiceImpl implements ChatService {

	private final ChatRepository chatRepository;

	private final UserRepository userRepository;

	private final UserService userService;

	private final ChatMapper mapper;

	public ChatServiceImpl(ChatRepository chatRepository, UserRepository userRepository, UserService userService,
			ChatMapper mapper) {
		this.chatRepository = chatRepository;
		this.userRepository = userRepository;
		this.userService = userService;
		this.mapper = mapper;
	}

	@Override
	public String createChat(Long senderId, Long receiverId) throws ServiceException {
		// search a chat
		Optional<ChatEntity> existingChat = chatRepository.findChatBetweenUsers(senderId, receiverId);
		if (existingChat.isPresent()) {
			return existingChat.get().getChatId();
		}

		// find user that want to send a message with a new user
		UserEntity sender = userService.getOneUserOrThrow(senderId);

		// find user that receive message
		UserEntity receiver = userService.getOneUserOrThrow(receiverId);

		// create chat
		ChatEntity chat = new ChatEntity();
		chat.setSender(sender);
		chat.setRecipient(receiver);

		ChatEntity savedChat = chatRepository.save(chat);
		return savedChat.getChatId();
	}

	@Override
	public Long getAuthenticatedUserId(Authentication authentication) throws ServiceException {
		String email = authentication.getName(); // because you log in with email

		UserEntity user = userService.getOneUserByEmailOrThrow(email);

		return user.getUserId();
	}

	@Override
	public List<ChatResponseDTO> getChatsByReceiverId(Authentication currentUser) throws ServiceException {

		Long currentUserId = getAuthenticatedUserId(currentUser);

		return chatRepository.findChatsByUserId(currentUserId).stream()
				.map(chat -> mapper.toChatResponse(chat, currentUserId)).toList();
	}

}
