package com.mx.dev.blog.spring_001_blog.notifiation.services;

import java.util.List;

import org.springframework.http.codec.ServerSentEvent;

import com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO;

import reactor.core.publisher.Flux;

public interface PushNotificationService {

	List<NotificationDTO> getNotifiations(Long userId);

	Flux<ServerSentEvent<List<NotificationDTO>>> getNotificationsByUserToId(Long userId);

}
