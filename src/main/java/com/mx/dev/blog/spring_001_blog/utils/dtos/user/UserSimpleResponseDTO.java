package com.mx.dev.blog.spring_001_blog.utils.dtos.user;

import java.time.LocalDateTime;

public class UserSimpleResponseDTO {

	/**
	 * user id
	 */
	private Long userId;

	/**
	 * username
	 */
	private String username;

	/**
	 * email
	 */
	private String email;

	/**
	 * profilePicture
	 */
	private String profilePicture;

	/**
	 * created at
	 */
	private LocalDateTime createdAt;

	/**
	 * 
	 */
	public UserSimpleResponseDTO() {
	}

	/**
	 * @param userId
	 * @param username
	 * @param email
	 * @param profilePicture
	 * @param createdAt
	 */
	public UserSimpleResponseDTO(Long userId, String username, String email, String profilePicture,
			LocalDateTime createdAt) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.profilePicture = profilePicture;
		this.createdAt = createdAt;
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
	 * return the value of the property profilePicture
	 *
	 * @return the profilePicture
	 */
	public String getProfilePicture() {
		return profilePicture;
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
	 * set the value of the property profilePicture
	 *
	 * @param profilePicture the profilePicture to set
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
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
