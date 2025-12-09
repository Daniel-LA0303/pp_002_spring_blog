package com.mx.dev.blog.spring_001_blog.notifiation.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;
import com.mx.dev.blog.spring_001_blog.notifiation.services.NotificationService;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@RequestMapping("/api/notification")
@RestController
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@PostMapping("/mark-all-notification-read")
	public ResponseEntity<?> changeAllNotificationStatusToRead(@RequestBody Map<String, Long> body) {

		Long userId = body.get("userId");

		System.out.println("userId " + userId);
		notificationService.changeAllNotificationsAsRead(userId);

		ApiResponse<String> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"All notifications marked as read." + userId, MethodEnum.POST, null, "Success", false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);

	}

	@GetMapping("/read/{notificationId}")
	public ResponseEntity<NotificationEntity> changeNotificationStatusToRead(@PathVariable Long notificationId)
			throws ServiceException {

		return ResponseEntity.ok(notificationService.changeNotifStatusToRead(notificationId));

	}

	@GetMapping("/get-all-notifications/{userId}")
	public ResponseEntity<ApiResponse<Page<NotificationDTO>>> getAllNotificationsByUserId(@PathVariable Long userId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size)
			throws ServiceException {

		Pageable pageable = PageRequest.of(page, size);

		Page<NotificationDTO> notificationDTOs = notificationService.getAllNotificationsByUserPaginated(userId,
				pageable);

		ApiResponse<Page<NotificationDTO>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/notification/get-all-notifications/" + userId, MethodEnum.GET,
				"Success to get all notifications.", notificationDTOs, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<NotificationEntity>> getNotificationsByUserId(@PathVariable Long userId) {

		return ResponseEntity.ok(notificationService.getNotificationsByUserIdNotRead(userId));

	}

	@PostMapping("/mark-read-notification")
	public ResponseEntity<?> markAsReadOneNotification(@RequestBody Map<String, Long> body) throws ServiceException {

		Long notificationId = body.get("notificationId");

		notificationService.changeNotifStatusToRead(notificationId);

		ApiResponse<String> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"Notification mark as read.", MethodEnum.POST, null, "Success", false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);

	}

}
