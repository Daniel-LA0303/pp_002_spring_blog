package com.mx.dev.blog.spring_001_blog.notifiation.services.impl;

import java.time.Duration;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfigurationSource;

import com.mx.dev.blog.spring_001_blog.notifiation.repository.NotificationRepository;
import com.mx.dev.blog.spring_001_blog.notifiation.services.NotificationService;
import com.mx.dev.blog.spring_001_blog.notifiation.services.PushNotificationService;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationsSSEResponseDTO;

import reactor.core.publisher.Flux;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

	private final CorsConfigurationSource corsConfigurationSource;

	private final NotificationService notificationService;

	private final NotificationRepository notificationRepository;

	public PushNotificationServiceImpl(NotificationService notificationService,
			NotificationRepository notificationRepository, CorsConfigurationSource corsConfigurationSource) {
		this.notificationService = notificationService;
		this.notificationRepository = notificationRepository;
		this.corsConfigurationSource = corsConfigurationSource;
	}

	/**
	 * get notifications form repository
	 */
	@Override
	@Transactional
	public NotificationsSSEResponseDTO getNotifiations(Long userId) {

		Pageable topFive = PageRequest.of(0, 5, Sort.by("createdAt").descending());

		List<NotificationDTO> notifications = notificationRepository.findNotificationsWithUserInfo(userId, topFive);
		notifications.forEach(x -> x.setDelivered(true));

		Long nNoti = notificationRepository.countUnreadNotifications(userId);

		return new NotificationsSSEResponseDTO(notifications, nNoti);
	}

	/**
	 * get notifications by user id
	 */
	@Override
	public Flux<ServerSentEvent<NotificationsSSEResponseDTO>> getNotificationsByUserToId(Long userId) {
		return Flux.interval(Duration.ofSeconds(3000)).map(seq -> {
			NotificationsSSEResponseDTO payload = getNotifiations(userId);
			return ServerSentEvent.<NotificationsSSEResponseDTO>builder().id(String.valueOf(seq))
					.event("user-list-event").data(payload).build();
		});
	}

}
