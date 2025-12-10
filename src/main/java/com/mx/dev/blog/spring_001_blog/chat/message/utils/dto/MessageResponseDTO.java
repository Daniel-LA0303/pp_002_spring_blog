package com.mx.dev.blog.spring_001_blog.chat.message.utils.dto;

import java.time.LocalDateTime;

import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageState;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageType;

public class MessageResponseDTO {

	private Long id;

	private String content;

	private MessageType type;

	private MessageState state;

	private Long senderId;

	private Long receiverId;

	private LocalDateTime createdAt;

	private byte[] media;

	/**
	 * 
	 */
	public MessageResponseDTO() {
	}

	/**
	 * @param id
	 * @param content
	 * @param type
	 * @param state
	 * @param senderId
	 * @param receiverId
	 * @param createdAt
	 * @param media
	 */
	public MessageResponseDTO(Long id, String content, MessageType type, MessageState state, Long senderId,
			Long receiverId, LocalDateTime createdAt, byte[] media) {
		this.id = id;
		this.content = content;
		this.type = type;
		this.state = state;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.createdAt = createdAt;
		this.media = media;
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
	 * return value of the property id
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
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
	 * return value of the property state
	 *
	 * @return the state
	 */
	public MessageState getState() {
		return state;
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
	 * set value of the property id
	 *
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * set value of the property state
	 *
	 * @param state the state to set
	 */
	public void setState(MessageState state) {
		this.state = state;
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
