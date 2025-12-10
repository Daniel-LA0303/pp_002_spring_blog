package com.mx.dev.blog.spring_001_blog.chat.chat.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.mx.dev.blog.spring_001_blog.chat.chat.utils.constants.ChatConstants;
import com.mx.dev.blog.spring_001_blog.chat.common.BaseAuditingEntity;
import com.mx.dev.blog.spring_001_blog.chat.message.entities.MessageEntity;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageState;
import com.mx.dev.blog.spring_001_blog.chat.message.utils.enums.MessageType;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;

@Entity
@Table(name = "chat_tbl")
@NamedQuery(name = ChatConstants.FIND_CHAT_BY_SENDER_ID, query = "SELECT DISTINCT c FROM ChatEntity c WHERE c.sender.id = :senderId OR c.recipient.id = :senderId ORDER BY createdDate DESC")
@NamedQuery(name = ChatConstants.FIND_CHAT_BY_SENDER_ID_AND_RECEIVER, query = "SELECT DISTINCT c FROM ChatEntity c WHERE (c.sender.id = :senderId AND c.recipient.id = :recipientId) OR (c.sender.id = :recipientId AND c.recipient.id = :senderId) ORDER BY createdDate DESC")
public class ChatEntity extends BaseAuditingEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "chat_id")
	private String chatId;

	@ManyToOne
	@JoinColumn(name = "sender_id")
	private UserEntity sender;

	@ManyToOne
	@JoinColumn(name = "recipient_id")
	private UserEntity recipient;

	@OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
	@OrderBy("createdDate DESC")
	private List<MessageEntity> messages;

	/**
	 * 
	 */
	public ChatEntity() {
	}

	/**
	 * @param chatId
	 * @param sender
	 * @param recipient
	 * @param messages
	 */
	public ChatEntity(String chatId, UserEntity sender, UserEntity recipient, List<MessageEntity> messages) {
		this.chatId = chatId;
		this.sender = sender;
		this.recipient = recipient;
		this.messages = messages;
	}

	/**
	 * return value of the property chatId
	 *
	 * @return the chatId
	 */
	public String getChatId() {
		return chatId;
	}

	// Returns the display name of the chat depending on who the current user is.
	// If the current user is the recipient, it returns the sender's username,
	// otherwise the recipient's username.
	@Transient
	public String getChatName(String senderId) {
		if (recipient.getUserId().equals(senderId)) {
			return sender.getUsername();
		}
		return recipient.getUsername();
	}

	// Returns the last message content for the chat.
	// If the last message is not text, it returns "Attachment". If there are no
	// messages, returns null.
	@Transient
	public String getLastMessage() {
		if (messages != null && !messages.isEmpty()) {
			if (messages.get(0).getType() != MessageType.TEXT) {
				return "Attachment";
			}
			return messages.get(0).getContent();
		}
		return null;
	}

	// Returns the timestamp of the last message in the chat.
	// If no messages exist, it returns null.
	@Transient
	public LocalDateTime getLastMessageTime() {
		if (messages != null && !messages.isEmpty()) {
			return messages.get(0).getCreatedDate();
		}
		return null;
	}

	/**
	 * return value of the property messages
	 *
	 * @return the messages
	 */
	public List<MessageEntity> getMessages() {
		return messages;
	}

	/**
	 * return value of the property recipient
	 *
	 * @return the recipient
	 */
	public UserEntity getRecipient() {
		return recipient;
	}

	/**
	 * return value of the property sender
	 *
	 * @return the sender
	 */
	public UserEntity getSender() {
		return sender;
	}

	// Returns the username of the other participant in the chat based on the given
	// userId.
	// If the sender is the same as the given userId, it returns the sender's
	// username, otherwise the recipient's username.
	@Transient
	public String getTargetChatName(String senderId) {
		if (sender.getUserId().equals(senderId)) {
			return sender.getUsername();
		}
		return recipient.getUsername();
	}

	// Returns the number of unread messages for the given user.
	// Counts messages where the user is the receiver and the message state is SENT.
	@Transient
	public long getUnreadMessages(String senderId) {
		return this.messages.stream().filter(m -> m.getReceiverId().equals(senderId))
				.filter(m -> MessageState.SENT == m.getState()).count();
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
	 * set value of the property messages
	 *
	 * @param messages the messages to set
	 */
	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}

	/**
	 * set value of the property recipient
	 *
	 * @param recipient the recipient to set
	 */
	public void setRecipient(UserEntity recipient) {
		this.recipient = recipient;
	}

	/**
	 * set value of the property sender
	 *
	 * @param sender the sender to set
	 */
	public void setSender(UserEntity sender) {
		this.sender = sender;
	}

}
