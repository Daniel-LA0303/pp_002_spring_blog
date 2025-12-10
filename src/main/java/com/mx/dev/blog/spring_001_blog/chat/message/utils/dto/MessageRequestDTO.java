package com.mx.dev.blog.spring_001_blog.chat.message.utils.dto;

import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageType;

public class MessageRequestDTO {

	private String content;

	private Long senderId;

	private Long receiverId;

	private MessageType type;

	private String chatId;

	/**
	 * 
	 */
	public MessageRequestDTO() {
	}

	/**
	 * @param content
	 * @param senderId
	 * @param receiverId
	 * @param type
	 * @param chatId
	 */
	public MessageRequestDTO(String content, Long senderId, Long receiverId, MessageType type, String chatId) {
		this.content = content;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.type = type;
		this.chatId = chatId;
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
	 * return value of the property content
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
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
	public MessageType getType() {
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
	 * set value of the property content
	 *
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	public void setType(MessageType type) {
		this.type = type;
	}

}
