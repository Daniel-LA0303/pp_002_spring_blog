package com.mx.dev.blog.spring_001_blog.notifiation.services;

import java.util.List;

import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface NotificationService {

	NotificationEntity changeNotifStatusToRead(Long notificationId) throws ServiceException;

	NotificationEntity createNotificationStorage(NotificationEntity notificationStorage);

	NotificationEntity getNotificationsById(Long notificationId) throws ServiceException;

	List<NotificationEntity> getNotificationsByUserId(Long userId);

	List<NotificationEntity> getNotificationsByUserIdNotRead(Long userId);

}
