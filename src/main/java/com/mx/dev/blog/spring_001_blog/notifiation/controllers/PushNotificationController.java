package com.mx.dev.blog.spring_001_blog.notifiation.controllers;

import java.util.List;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.notifiation.services.PushNotificationService;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/push-notifications")
public class PushNotificationController {

	private final PushNotificationService pushNotificationService;

	public PushNotificationController(PushNotificationService pushNotificationService) {
		this.pushNotificationService = pushNotificationService;
	}

	@GetMapping("/{userId}")
	public Flux<ServerSentEvent<List<NotificationDTO>>> streamLastMessage(@PathVariable Long userId) {
		return pushNotificationService.getNotificationsByUserToId(userId);
	}

}
