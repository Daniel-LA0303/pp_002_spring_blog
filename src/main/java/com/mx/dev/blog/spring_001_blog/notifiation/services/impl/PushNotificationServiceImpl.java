package com.mx.dev.blog.spring_001_blog.notifiation.services.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfigurationSource;

import com.mx.dev.blog.spring_001_blog.notifiation.repository.NotificationRepository;
import com.mx.dev.blog.spring_001_blog.notifiation.services.NotificationService;
import com.mx.dev.blog.spring_001_blog.notifiation.services.PushNotificationService;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

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
	public List<NotificationDTO> getNotifiations(Long userId) {

		List<NotificationDTO> notifications = notificationRepository.findNotificationsWithUserInfo(userId);

		notifications.forEach(x -> x.setDelivered(true));
		return notifications;
	}

	/**
	 * get notifications by user id
	 */
	@Override
	public Flux<ServerSentEvent<List<NotificationDTO>>> getNotificationsByUserToId(Long userId) {

		if (userId != null) {

			return Flux.interval(Duration.ofSeconds(15)).publishOn(Schedulers.boundedElastic())
					.map(sequence -> ServerSentEvent.<List<NotificationDTO>>builder().id(String.valueOf(sequence))
							.event("user-list-event").data(getNotifiations(userId)).build());
		}

		System.out.println("with out userid");
		return Flux.interval(Duration.ofSeconds(15)).map(sequence -> ServerSentEvent.<List<NotificationDTO>>builder()
				.id(String.valueOf(sequence)).event("user-list-event").data(new ArrayList<>()).build());
	}

}
