package com.mx.dev.blog.spring_001_blog.chat.notificationmessage.utils.dto;

import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageType;
import com.mx.dev.blog.spring_001_blog.chat.notificationmessage.utils.enums.NotificationMessageType;

public class NotificationMessageDTO {

	private String chatId;

	private String content;

	private Long senderId;

	private Long receiverId;

	private String chatName;

	private MessageType messageType;

	private NotificationMessageType type;

	private byte[] media;

	/**
	 * 
	 */
	public NotificationMessageDTO() {
	}

	/**
	 * @param chatId
	 * @param content
	 * @param senderId
	 * @param receiverId
	 * @param chatName
	 * @param messageType
	 * @param type
	 * @param media
	 */
	public NotificationMessageDTO(String chatId, String content, Long senderId, Long receiverId, String chatName,
			MessageType messageType, NotificationMessageType type, byte[] media) {
		this.chatId = chatId;
		this.content = content;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.chatName = chatName;
		this.messageType = messageType;
		this.type = type;
		this.media = media;
	}

	/**
	 * return value of the property chatId
	 *
	 * @return the chatId
	 */
	public String getChatId() {
		return chatId;
	}

	/**
	 * return value of the property chatName
	 *
	 * @return the chatName
	 */
	public String getChatName() {
		return chatName;
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
	 * return value of the property media
	 *
	 * @return the media
	 */
	public byte[] getMedia() {
		return media;
	}

	/**
	 * return value of the property messageType
	 *
	 * @return the messageType
	 */
	public MessageType getMessageType() {
		return messageType;
	}

	/**
	 * return value of the property receiverId
	 *
	 * @return the receiverId
	 */
	public Long getReceiverId() {
		return receiverId;
	}

	/**
	 * return value of the property senderId
	 *
	 * @return the senderId
	 */
	public Long getSenderId() {
		return senderId;
	}

	/**
	 * return value of the property type
	 *
	 * @return the type
	 */
	public NotificationMessageType getType() {
		return type;
	}

	/**
	 * set value of the property chatId
	 *
	 * @param chatId the chatId to set
	 */
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	/**
	 * set value of the property chatName
	 *
	 * @param chatName the chatName to set
	 */
	public void setChatName(String chatName) {
		this.chatName = chatName;
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
	 * set value of the property media
	 *
	 * @param media the media to set
	 */
	public void setMedia(byte[] media) {
		this.media = media;
	}

	/**
	 * set value of the property messageType
	 *
	 * @param messageType the messageType to set
	 */
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	/**
	 * set value of the property receiverId
	 *
	 * @param receiverId the receiverId to set
	 */
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * set value of the property senderId
	 *
	 * @param senderId the senderId to set
	 */
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	/**
	 * set value of the property type
	 *
	 * @param type the type to set
	 */
	public void setType(NotificationMessageType type) {
		this.type = type;
	}

}
