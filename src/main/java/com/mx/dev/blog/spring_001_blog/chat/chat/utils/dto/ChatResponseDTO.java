package com.mx.dev.blog.spring_001_blog.chat.chat.utils.dto;

import java.time.LocalDateTime;

public class ChatResponseDTO {

	/**
	 * chat id
	 */
	private String id;

	/**
	 * name
	 */
	private String name;

	/**
	 * unread messages count
	 */
	private long unreadCount;

	/**
	 * last message
	 */
	private String lastMessage;

	/**
	 * last message time
	 */
	private LocalDateTime lastMessageTime;

	/**
	 * is online
	 */
	private boolean isRecipientOnline;

	/**
	 * sender
	 */
	private Long senderId;

	/**
	 * receiver
	 */
	private Long receiverId;

	/**
	 * 
	 */
	public ChatResponseDTO() {
	}

	/**
	 * @param id
	 * @param name
	 * @param unreadCount
	 * @param lastMessage
	 * @param lastMessageTime
	 * @param isRecipientOnline
	 * @param senderId
	 * @param receiverId
	 */
	public ChatResponseDTO(String id, String name, long unreadCount, String lastMessage, LocalDateTime lastMessageTime,
			boolean isRecipientOnline, Long senderId, Long receiverId) {
		this.id = id;
		this.name = name;
		this.unreadCount = unreadCount;
		this.lastMessage = lastMessage;
		this.lastMessageTime = lastMessageTime;
		this.isRecipientOnline = isRecipientOnline;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

	/**
	 * return value of the property id
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * return value of the property lastMessage
	 *
	 * @return the lastMessage
	 */
	public String getLastMessage() {
		return lastMessage;
	}

	/**
	 * return value of the property lastMessageTime
	 *
	 * @return the lastMessageTime
	 */
	public LocalDateTime getLastMessageTime() {
		return lastMessageTime;
	}

	/**
	 * return value of the property name
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * return value of the property unreadCount
	 *
	 * @return the unreadCount
	 */
	public long getUnreadCount() {
		return unreadCount;
	}

	/**
	 * return value of the property isRecipientOnline
	 *
	 * @return the isRecipientOnline
	 */
	public boolean isRecipientOnline() {
		return isRecipientOnline;
	}

	/**
	 * set value of the property id
	 *
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * set value of the property lastMessage
	 *
	 * @param lastMessage the lastMessage to set
	 */
	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

	/**
	 * set value of the property lastMessageTime
	 *
	 * @param lastMessageTime the lastMessageTime to set
	 */
	public void setLastMessageTime(LocalDateTime lastMessageTime) {
		this.lastMessageTime = lastMessageTime;
	}

	/**
	 * set value of the property name
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * set value of the property isRecipientOnline
	 *
	 * @param isRecipientOnline the isRecipientOnline to set
	 */
	public void setRecipientOnline(boolean isRecipientOnline) {
		this.isRecipientOnline = isRecipientOnline;
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
	 * set value of the property unreadCount
	 *
	 * @param unreadCount the unreadCount to set
	 */
	public void setUnreadCount(long unreadCount) {
		this.unreadCount = unreadCount;
	}

}
