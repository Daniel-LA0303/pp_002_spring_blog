package com.mx.dev.blog.spring_001_blog.notifiation.services.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;
import com.mx.dev.blog.spring_001_blog.notifiation.repository.NotificationRepository;
import com.mx.dev.blog.spring_001_blog.notifiation.services.NotificationService;
import com.mx.dev.blog.spring_001_blog.notifiation.services.PushNotificationService;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

	private final NotificationService notificationService;

	private final NotificationRepository notificationRepository;

	public PushNotificationServiceImpl(NotificationService notificationService,
			NotificationRepository notificationRepository) {
		this.notificationService = notificationService;
		this.notificationRepository = notificationRepository;
	}

	/**
	 * get notifications form repository
	 */
	@Override
	public List<NotificationEntity> getNotifiations(Long userId) {

		List<NotificationEntity> notifications = notificationRepository.findByUserToIdAndDeliveredFalse(userId);

		notifications.forEach(x -> x.setDelivered(true));

		return notifications;
	}

	/**
	 * get notifications by user id
	 */
	@Override
	public Flux<ServerSentEvent<List<NotificationEntity>>> getNotificationsByUserToId(Long userId) {

		if (userId != null) {
			return Flux.interval(Duration.ofSeconds(1)).publishOn(Schedulers.boundedElastic())
					.map(sequence -> ServerSentEvent.<List<NotificationEntity>>builder().id(String.valueOf(sequence))
							.event("user-list-event").data(getNotifiations(userId)).build());
		}

		return Flux.interval(Duration.ofSeconds(1)).map(sequence -> ServerSentEvent.<List<NotificationEntity>>builder()
				.id(String.valueOf(sequence)).event("user-list-event").data(new ArrayList<>()).build());
	}

}
