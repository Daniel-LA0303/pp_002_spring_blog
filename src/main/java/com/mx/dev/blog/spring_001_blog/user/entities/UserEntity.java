package com.mx.dev.blog.spring_001_blog.user.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mx.dev.blog.spring_001_blog.chat.chat.entities.ChatEntity;

@Entity
@Table(name = "user_tbl")
public class UserEntity {

	private static final int LAST_ACTIVATE_INTERVAL = 5;

	/**
	 * id entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	/**
	 * username
	 */
	@Column(name = "username")
	private String username;

	/**
	 * email
	 */
	@Column(name = "email")
	private String email;

	/**
	 * password
	 */
	@Column(name = "password")
	private String password;

	/**
	 * token to confirm
	 */
	@Column(name = "token")
	private String token;

	/**
	 * confirm with a boolean
	 */
	@Column(name = "confirm")
	private Boolean confirm;

	/**
	 * created at
	 */
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "last_seen")
	private LocalDateTime lastSeen;

	/**
	 * updated at
	 */
	@Column(name = "update_at")
	private LocalDateTime updatedAt;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_role_tbl", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles = new HashSet<>();

	@OneToMany(mappedBy = "sender")
	private List<ChatEntity> chatsAsSender;

	@OneToMany(mappedBy = "recipient")
	private List<ChatEntity> chatsAsRecipient;

	/**
	 * 
	 */
	public UserEntity() {
	}

	/**
	 * @param userId
	 * @param username
	 * @param email
	 * @param password
	 * @param token
	 * @param confirm
	 * @param createdAt
	 * @param lastSeen
	 * @param updatedAt
	 * @param roles
	 * @param chatsAsSender
	 * @param chatsAsRecipient
	 */
	public UserEntity(Long userId, String username, String email, String password, String token, Boolean confirm,
			LocalDateTime createdAt, LocalDateTime lastSeen, LocalDateTime updatedAt, Set<RoleEntity> roles,
			List<ChatEntity> chatsAsSender, List<ChatEntity> chatsAsRecipient) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.token = token;
		this.confirm = confirm;
		this.createdAt = createdAt;
		this.lastSeen = lastSeen;
		this.updatedAt = updatedAt;
		this.roles = roles;
		this.chatsAsSender = chatsAsSender;
		this.chatsAsRecipient = chatsAsRecipient;
	}

	/**
	 * @param userId
	 * @param username
	 * @param email
	 * @param password
	 * @param token
	 * @param confirm
	 * @param createdAt
	 * @param updatedAt
	 * @param roles
	 */
	public UserEntity(Long userId, String username, String email, String password, String token, Boolean confirm,
			LocalDateTime createdAt, LocalDateTime updatedAt, Set<RoleEntity> roles) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.token = token;
		this.confirm = confirm;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roles = roles;
	}

	/**
	 * return value of the property lastActivateInterval
	 *
	 * @return the lastActivateInterval
	 */
	public static int getLastActivateInterval() {
		return LAST_ACTIVATE_INTERVAL;
	}

	/**
	 * return value of the property chatsAsRecipient
	 *
	 * @return the chatsAsRecipient
	 */
	public List<ChatEntity> getChatsAsRecipient() {
		return chatsAsRecipient;
	}

	/**
	 * return value of the property chatsAsSender
	 *
	 * @return the chatsAsSender
	 */
	public List<ChatEntity> getChatsAsSender() {
		return chatsAsSender;
	}

	/**
	 * return value of the property confirm
	 *
	 * @return the confirm
	 */
	public Boolean getConfirm() {
		return confirm;
	}

	/**
	 * return the value of the property createdAt
	 *
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * return the value of the property email
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * return value of the property lastSeen
	 *
	 * @return the lastSeen
	 */
	public LocalDateTime getLastSeen() {
		return lastSeen;
	}

	/**
	 * return the value of the property password
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * return the value of the property roles
	 *
	 * @return the roles
	 */
	public Set<RoleEntity> getRoles() {
		return roles;
	}

	/**
	 * return value of the property token
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * return the value of the property updatedAt
	 *
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * return the value of the property userId
	 *
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * return the value of the property username
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	@Transient
	public boolean isUserOnline() {
		return lastSeen != null && lastSeen.isAfter(LocalDateTime.now().minusMinutes(LAST_ACTIVATE_INTERVAL));
	}

	/**
	 * set value of the property chatsAsRecipient
	 *
	 * @param chatsAsRecipient the chatsAsRecipient to set
	 */
	public void setChatsAsRecipient(List<ChatEntity> chatsAsRecipient) {
		this.chatsAsRecipient = chatsAsRecipient;
	}

	/**
	 * set value of the property chatsAsSender
	 *
	 * @param chatsAsSender the chatsAsSender to set
	 */
	public void setChatsAsSender(List<ChatEntity> chatsAsSender) {
		this.chatsAsSender = chatsAsSender;
	}

	/**
	 * set value of the property confirm
	 *
	 * @param confirm the confirm to set
	 */
	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	/**
	 * set the value of the property createdAt
	 *
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * set the value of the property email
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * set value of the property lastSeen
	 *
	 * @param lastSeen the lastSeen to set
	 */
	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
	}

	/**
	 * set the value of the property password
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * set the value of the property roles
	 *
	 * @param roles the roles to set
	 */
	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	/**
	 * set value of the property token
	 *
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * set the value of the property updatedAt
	 *
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * set the value of the property userId
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * set the value of the property username
	 *
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
