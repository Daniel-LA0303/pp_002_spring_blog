package com.mx.dev.blog.spring_001_blog.notifiation.services;

import java.util.List;

import org.springframework.http.codec.ServerSentEvent;

import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;

import reactor.core.publisher.Flux;

public interface PushNotificationService {

	List<NotificationEntity> getNotifiations(Long userId);

	Flux<ServerSentEvent<List<NotificationEntity>>> getNotificationsByUserToId(Long userId);

}
