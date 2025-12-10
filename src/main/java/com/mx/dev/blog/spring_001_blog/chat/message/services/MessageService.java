package com.mx.dev.blog.spring_001_blog.chat.message.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.chat.chat.entities.ChatEntity;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.dto.MessageRequestDTO;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.dto.MessageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface MessageService {

	List<MessageResponseDTO> findChatMessages(String chatId);

	Long getAuthenticatedUserId(Authentication authentication) throws ServiceException;

	Long getRecipientId(ChatEntity chat, Authentication authentication) throws ServiceException;

	Long getSenderId(ChatEntity chat, Authentication authentication) throws ServiceException;

	void saveMessage(MessageRequestDTO messageRequest);

	void setMessagesToSeen(String chatId, Authentication authentication) throws ServiceException;

	void uploadMediaMessage(String chatId, MultipartFile file, Authentication authentication) throws ServiceException;

}
