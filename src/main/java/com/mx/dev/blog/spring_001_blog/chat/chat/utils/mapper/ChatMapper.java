package com.mx.dev.blog.spring_001_blog.chat.chat.utils.mapper;

import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.chat.chat.entities.ChatEntity;
import com.mx.dev.blog.spring_001_blog.chat.chat.utils.dto.ChatResponseDTO;

@Service
public class ChatMapper {

	// convert chat entity to chat response
	public ChatResponseDTO toChatResponse(ChatEntity chat, Long senderId) {
		ChatResponseDTO response = new ChatResponseDTO();
		response.setId(chat.getChatId());
		response.setName(chat.getChatName(senderId));
		response.setUnreadCount(chat.getUnreadMessages(senderId));
		response.setLastMessage(chat.getLastMessage());
		response.setLastMessageTime(chat.getLastMessageTime());
		response.setRecipientOnline(chat.getRecipient().isUserOnline());
		response.setSenderId(chat.getSender().getUserId());
		response.setReceiverId(chat.getRecipient().getUserId());
		return response;
	}

}
