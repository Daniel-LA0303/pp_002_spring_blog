package com.mx.dev.blog.spring_001_blog.notifiation.services;

import org.springframework.http.codec.ServerSentEvent;

import com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationsSSEResponseDTO;

import reactor.core.publisher.Flux;

public interface PushNotificationService {

	NotificationsSSEResponseDTO getNotifiations(Long userId);

	Flux<ServerSentEvent<NotificationsSSEResponseDTO>> getNotificationsByUserToId(Long userId);

}
