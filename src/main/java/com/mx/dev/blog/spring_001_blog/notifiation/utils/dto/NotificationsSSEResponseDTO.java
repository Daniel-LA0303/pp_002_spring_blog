package com.mx.dev.blog.spring_001_blog.notifiation.utils.dto;

import java.util.List;

public class NotificationsSSEResponseDTO {

	private List<NotificationDTO> notifications;

	private Long numberNotifications;

	/**
	 * 
	 */
	public NotificationsSSEResponseDTO() {
	}

	/**
	 * @param notifications
	 * @param numberNotifications
	 */
	public NotificationsSSEResponseDTO(List<NotificationDTO> notifications, Long numberNotifications) {
		this.notifications = notifications;
		this.numberNotifications = numberNotifications;
	}

	/**
	 * return value of the property notifications
	 *
	 * @return the notifications
	 */
	public List<NotificationDTO> getNotifications() {
		return notifications;
	}

	/**
	 * return value of the property numberNotifications
	 *
	 * @return the numberNotifications
	 */
	public Long getNumberNotifications() {
		return numberNotifications;
	}

	/**
	 * set value of the property notifications
	 *
	 * @param notifications the notifications to set
	 */
	public void setNotifications(List<NotificationDTO> notifications) {
		this.notifications = notifications;
	}

	/**
	 * set value of the property numberNotifications
	 *
	 * @param numberNotifications the numberNotifications to set
	 */
	public void setNumberNotifications(Long numberNotifications) {
		this.numberNotifications = numberNotifications;
	}

}
