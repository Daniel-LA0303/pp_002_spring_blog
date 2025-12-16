package com.mx.dev.blog.spring_001_blog.chat.message.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.mx.dev.blog.spring_001_blog.chat.chat.entities.ChatEntity;
import com.mx.dev.blog.spring_001_blog.chat.common.BaseAuditingEntity;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.constants.MessageConstants;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageState;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageType;

@Entity
@Table(name = "messages_tbl")
@NamedQuery(name = MessageConstants.FIND_MESSAGES_BY_CHAT_ID, query = "SELECT m FROM MessageEntity m WHERE m.chat.id = :chatId ORDER BY m.createdDate")
@NamedQuery(name = MessageConstants.SET_MESSAGES_TO_SEEN_BY_CHAT, query = "UPDATE MessageEntity SET state = :newState WHERE chat.id = :chatId")
public class MessageEntity extends BaseAuditingEntity {

	// pagination extremely efficient with @SequenceGenerator
	@Id
	@SequenceGenerator(name = "msg_seq", sequenceName = "msg_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_seq")
	@Column(name = "message_id")
	private Long messageId;

	/**
	 * content
	 */
	@Column(name = "content")
	private String content;

	/**
	 * state message
	 */
	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private MessageState state;

	/**
	 * type message
	 */
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private MessageType type;

	/**
	 * chat
	 */
	@ManyToOne
	@JoinColumn(name = "chat_id")
	private ChatEntity chat;

	/**
	 * sender
	 */
	@Column(name = "sender_id")
	private Long senderId;

	/**
	 * receiver
	 */
	@Column(name = "receiver_id")
	private Long receiverId;

	/**
	 * media
	 */
	@Column(name = "media")
	private String media;

	/**
	 * 
	 */
	public MessageEntity() {
	}

	/**
	 * @param messageId
	 * @param content
	 * @param state
	 * @param type
	 * @param chat
	 * @param senderId
	 * @param receiverId
	 * @param media
	 */
	public MessageEntity(Long messageId, String content, MessageState state, MessageType type, ChatEntity chat,
			Long senderId, Long receiverId, String media) {
		this.messageId = messageId;
		this.content = content;
		this.state = state;
		this.type = type;
		this.chat = chat;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.media = media;
	}

	/**
	 * return the value of the property chat
	 *
	 * @return the chat
	 */
	public ChatEntity getChat() {
		return chat;
	}

	/**
	 * return the value of the property content
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * return the value of the property media
	 *
	 * @return the media
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * return the value of the property messageId
	 *
	 * @return the messageId
	 */
	public Long getMessageId() {
		return messageId;
	}

	/**
	 * return the value of the property receiverId
	 *
	 * @return the receiverId
	 */
	public Long getReceiverId() {
		return receiverId;
	}

	/**
	 * return the value of the property senderId
	 *
	 * @return the senderId
	 */
	public Long getSenderId() {
		return senderId;
	}

	/**
	 * return the value of the property state
	 *
	 * @return the state
	 */
	public MessageState getState() {
		return state;
	}

	/**
	 * return the value of the property type
	 *
	 * @return the type
	 */
	public MessageType getType() {
		return type;
	}

	/**
	 * set the value of the property chat
	 *
	 * chat the chat to set
	 */
	public void setChat(ChatEntity chat) {
		this.chat = chat;
	}

	/**
	 * set the value of the property content
	 *
	 * content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * set the value of the property media
	 *
	 * media the media to set
	 */
	public void setMedia(String media) {
		this.media = media;
	}

	/**
	 * set the value of the property messageId
	 *
	 * messageId the messageId to set
	 */
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	/**
	 * set the value of the property receiverId
	 *
	 * receiverId the receiverId to set
	 */
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * set the value of the property senderId
	 *
	 * senderId the senderId to set
	 */
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	/**
	 * set the value of the property state
	 *
	 * state the state to set
	 */
	public void setState(MessageState state) {
		this.state = state;
	}

	/**
	 * set the value of the property type
	 *
	 * type the type to set
	 */
	public void setType(MessageType type) {
		this.type = type;
	}

}
