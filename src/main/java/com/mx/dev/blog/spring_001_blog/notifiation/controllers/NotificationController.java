package com.mx.dev.blog.spring_001_blog.notifiation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;
import com.mx.dev.blog.spring_001_blog.notifiation.services.NotificationService;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@RequestMapping("/api/notification")
@RestController
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@GetMapping("/read/{notificationId}")
	public ResponseEntity<NotificationEntity> changeNotificationStatusToRead(@PathVariable Long notificationId)
			throws ServiceException {

		return ResponseEntity.ok(notificationService.changeNotifStatusToRead(notificationId));

	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<NotificationEntity>> getNotificationsByUserId(@PathVariable Long userId) {

		return ResponseEntity.ok(notificationService.getNotificationsByUserIdNotRead(userId));

	}

}
