package com.mx.dev.blog.spring_001_blog.chat.notificationmessage.utils.mappers;

import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.chat.config.file.FileUtils;
import com.mx.dev.blog.spring_001_blog.chat.message.entities.MessageEntity;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.dto.MessageResponseDTO;

@Service
public class MessageMapper {

	// from message entity to message response
	public MessageResponseDTO toMessageResponse(MessageEntity message) {
		MessageResponseDTO response = new MessageResponseDTO();
		response.setId(message.getMessageId());
		response.setContent(message.getContent());
		response.setSenderId(message.getSenderId());
		response.setReceiverId(message.getReceiverId());
		response.setType(message.getType());
		response.setState(message.getState());
		response.setCreatedAt(message.getCreatedDate());
		response.setMedia(FileUtils.readFileFromLocation(message.getMedia()));
		return response;
	}
}
