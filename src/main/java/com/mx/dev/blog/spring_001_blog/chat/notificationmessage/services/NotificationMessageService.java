package com.mx.dev.blog.spring_001_blog.chat.notificationmessage.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.chat.notificationmessage.utils.dto.NotificationMessageDTO;

@Service
public class NotificationMessageService {

	private static final Logger log = LoggerFactory.getLogger(NotificationMessageService.class);

	// send message in web socket
	private final SimpMessagingTemplate messagingTemplate;

	public NotificationMessageService(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	// send a message to user
	public void sendNotification(Long userId, NotificationMessageDTO notification) {
		System.out.println("======================================");
		log.info("Sending WS notification to {} with payload {}", userId, notification);
		messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/chat", notification);
	}

}
