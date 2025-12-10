package com.mx.dev.blog.spring_001_blog.notifiation.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.dto.NotificationDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface NotificationService {

	void changeAllNotificationsAsRead(Long userId);

	NotificationEntity changeNotifStatusToRead(Long notificationId) throws ServiceException;

	NotificationEntity createNotificationStorage(NotificationEntity notificationStorage);

	Page<NotificationDTO> getAllNotificationsByUserPaginated(Long userId, Pageable pageable);

	NotificationEntity getNotificationsById(Long notificationId) throws ServiceException;

	List<NotificationEntity> getNotificationsByUserId(Long userId);

	List<NotificationEntity> getNotificationsByUserIdNotRead(Long userId);

}
