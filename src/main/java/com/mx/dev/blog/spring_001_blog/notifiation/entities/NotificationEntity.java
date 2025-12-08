package com.mx.dev.blog.spring_001_blog.notifiation.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mx.dev.blog.spring_001_blog.notifiation.utils.enums.NotificationType;

@Table(name = "notification_tbl")
@Entity
public class NotificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id")
	private Long notificationId;

	@Column(name = "content")
	private String content;

	@Column(name = "user_to_id")
	private Long userToId;

	@Column(name = "user_from_id")
	private Long userFromId;

	@Column(name = "notification_type")
	private NotificationType notificationType;

	@Column(name = "delivered")
	private Boolean delivered;

	@Column(name = "read")
	private Boolean read;

	/**
	 * created at
	 */
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	/**
	 * updated at
	 */
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	/**
	 * 
	 */
	public NotificationEntity() {
	}

	/**
	 * @param notificationId
	 * @param content
	 * @param userToId
	 * @param userFromId
	 * @param notificationType
	 * @param delivered
	 * @param read
	 * @param createdAt
	 * @param updatedAt
	 */
	public NotificationEntity(Long notificationId, String content, Long userToId, Long userFromId,
			NotificationType notificationType, Boolean delivered, Boolean read, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.notificationId = notificationId;
		this.content = content;
		this.userToId = userToId;
		this.userFromId = userFromId;
		this.notificationType = notificationType;
		this.delivered = delivered;
		this.read = read;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	 * return value of the property read
	 *
	 * @return the read
	 */
	public Boolean getRead() {
		return read;
	}

	/**
	 * return value of the property updatedAt
	 *
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
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
	 * set value of the property read
	 *
	 * @param read the read to set
	 */
	public void setRead(Boolean read) {
		this.read = read;
	}

	/**
	 * set value of the property updatedAt
	 *
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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
