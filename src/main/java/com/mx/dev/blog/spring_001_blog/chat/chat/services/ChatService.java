package com.mx.dev.blog.spring_001_blog.chat.chat.services;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.mx.dev.blog.spring_001_blog.chat.chat.utils.dto.ChatResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface ChatService {

	String createChat(Long senderId, Long receiverId) throws ServiceException;

	Long getAuthenticatedUserId(Authentication authentication) throws ServiceException;

	List<ChatResponseDTO> getChatsByReceiverId(Authentication currentUser) throws ServiceException;

}
