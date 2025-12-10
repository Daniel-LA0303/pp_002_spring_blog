package com.mx.dev.blog.spring_001_blog.notifiation.utils.dto;

import java.time.LocalDateTime;

import com.mx.dev.blog.spring_001_blog.notifiation.utils.enums.NotificationTargetType;
import com.mx.dev.blog.spring_001_blog.notifiation.utils.enums.NotificationType;

public class NotificationDTO {

	private Long notificationId;

	private String content;

	private Long userToId;

	private Long userFromId;

	private NotificationType notificationType;

	private Boolean delivered;

	private Boolean read;

	private Long targetId;

	private NotificationTargetType targetType;

	private LocalDateTime createdAt;

	private NotificationUserInfo notificationUserInfo;

	/**
	 * 
	 */
	public NotificationDTO() {
	}

	/**
	 * @param notificationId
	 * @param content
	 * @param userToId
	 * @param userFromId
	 * @param notificationType
	 * @param delivered
	 * @param read
	 * @param targetId
	 * @param targetType
	 * @param createdAt
	 * @param notificationUserInfo
	 */
	public NotificationDTO(Long notificationId, String content, Long userToId, Long userFromId,
			NotificationType notificationType, Boolean delivered, Boolean read, Long targetId,
			NotificationTargetType targetType, LocalDateTime createdAt, Long userId, String profilePicture,
			String username) {
		this.notificationId = notificationId;
		this.content = content;
		this.userToId = userToId;
		this.userFromId = userFromId;
		this.notificationType = notificationType;
		this.delivered = delivered;
		this.read = read;
		this.targetId = targetId;
		this.targetType = targetType;
		this.createdAt = createdAt;
		this.notificationUserInfo = new NotificationUserInfo(userId, profilePicture, username);
	}

	/**
	 * return value of the property content
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * return value of the property createdAt
	 *
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * return value of the property delivered
	 *
	 * @return the delivered
	 */
	public Boolean getDelivered() {
		return delivered;
	}

	/**
	 * return value of the property notificationId
	 *
	 * @return the notificationId
	 */
	public Long getNotificationId() {
		return notificationId;
	}

	/**
	 * return value of the property notificationType
	 *
	 * @return the notificationType
	 */
	public NotificationType getNotificationType() {
		return notificationType;
	}

	/**
	 * return value of the property notificationUserInfo
	 *
	 * @return the notificationUserInfo
	 */
	public NotificationUserInfo getNotificationUserInfo() {
		return notificationUserInfo;
	}

	/**
	 * return value of the property read
	 *
	 * @return the read
	 */
	public Boolean getRead() {
		return read;
	}

	/**
	 * return value of the property targetId
	 *
	 * @return the targetId
	 */
	public Long getTargetId() {
		return targetId;
	}

	/**
	 * return value of the property targetType
	 *
	 * @return the targetType
	 */
	public NotificationTargetType getTargetType() {
		return targetType;
	}

	/**
	 * return value of the property userFromId
	 *
	 * @return the userFromId
	 */
	public Long getUserFromId() {
		return userFromId;
	}

	/**
	 * return value of the property userToId
	 *
	 * @return the userToId
	 */
	public Long getUserToId() {
		return userToId;
	}

	/**
	 * set value of the property content
	 *
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * set value of the property createdAt
	 *
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * set value of the property delivered
	 *
	 * @param delivered the delivered to set
	 */
	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}

	/**
	 * set value of the property notificationId
	 *
	 * @param notificationId the notificationId to set
	 */
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	/**
	 * set value of the property notificationType
	 *
	 * @param notificationType the notificationType to set
	 */
	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * set value of the property notificationUserInfo
	 *
	 * @param notificationUserInfo the notificationUserInfo to set
	 */
	public void setNotificationUserInfo(NotificationUserInfo notificationUserInfo) {
		this.notificationUserInfo = notificationUserInfo;
	}

	/**
	 * set value of the property read
	 *
	 * @param read the read to set
	 */
	public void setRead(Boolean read) {
		this.read = read;
	}

	/**
	 * set value of the property targetId
	 *
	 * @param targetId the targetId to set
	 */
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	/**
	 * set value of the property targetType
	 *
	 * @param targetType the targetType to set
	 */
	public void setTargetType(NotificationTargetType targetType) {
		this.targetType = targetType;
	}

	/**
	 * set value of the property userFromId
	 *
	 * @param userFromId the userFromId to set
	 */
	public void setUserFromId(Long userFromId) {
		this.userFromId = userFromId;
	}

	/**
	 * set value of the property userToId
	 *
	 * @param userToId the userToId to set
	 */
	public void setUserToId(Long userToId) {
		this.userToId = userToId;
	}

}
