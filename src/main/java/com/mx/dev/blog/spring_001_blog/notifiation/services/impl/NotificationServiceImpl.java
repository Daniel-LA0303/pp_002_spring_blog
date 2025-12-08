package com.mx.dev.blog.spring_001_blog.notifiation.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.notifiation.entities.NotificationEntity;
import com.mx.dev.blog.spring_001_blog.notifiation.repository.NotificationRepository;
import com.mx.dev.blog.spring_001_blog.notifiation.services.NotificationService;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;

	public NotificationServiceImpl(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	/**
	 * change status of notification
	 */
	@Override
	public NotificationEntity changeNotifStatusToRead(Long notificationId) throws ServiceException {

		NotificationEntity notificationEntity = notificationRepository.findByNotificationId(notificationId)
				.orElseThrow(() -> new ServiceException("Notificaction not found.",
						ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/notification", MethodEnum.POST));

		notificationEntity.setRead(true);

		return notificationEntity;
	}

	/**
	 * save a notification
	 */
	@Override
	public NotificationEntity createNotificationStorage(NotificationEntity notificationStorage) {

		return notificationRepository.save(notificationStorage);
	}

	/**
	 * get one notification by id
	 * 
	 * @throws ServiceException
	 */
	@Override
	public NotificationEntity getNotificationsById(Long notificationId) throws ServiceException {

		return notificationRepository.findByNotificationId(notificationId)
				.orElseThrow(() -> new ServiceException("Notificaction not found.",
						ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/notification", MethodEnum.POST));
	}

	/**
	 * get notifications by user
	 */
	@Override
	public List<NotificationEntity> getNotificationsByUserId(Long userId) {

		return notificationRepository.findByUserToId(userId);
	}

	/**
	 * get notifications that not read yet
	 */
	@Override
	public List<NotificationEntity> getNotificationsByUserIdNotRead(Long userId) {
		return notificationRepository.findByUserToIdAndDeliveredFalse(userId);
	}

}
