package com.mx.dev.blog.spring_001_blog.utils.dtos.user;

public class UserSearchChatDTO {

	private Long userId;

	private String username;

	private String profileImage;

	/**
	 * 
	 */
	public UserSearchChatDTO() {
	}

	/**
	 * @param userId
	 * @param username
	 * @param profileImage
	 */
	public UserSearchChatDTO(Long userId, String username, String profileImage) {
		this.userId = userId;
		this.username = username;
		this.profileImage = profileImage;
	}

	/**
	 * return the value of the property profileImage
	 *
	 * @return the profileImage
	 */
	public String getProfileImage() {
		return profileImage;
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
	 * set the value of the property profileImage
	 *
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
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
