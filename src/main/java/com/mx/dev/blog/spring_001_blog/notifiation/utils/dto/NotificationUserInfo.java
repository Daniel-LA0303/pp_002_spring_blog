package com.mx.dev.blog.spring_001_blog.notifiation.utils.dto;

public class NotificationUserInfo {

	private Long userId;

	private String profileImage;

	private String username;

	/**
	 * 
	 */
	public NotificationUserInfo() {
	}

	/**
	 * @param userId
	 * @param profileImage
	 * @param username
	 */
	public NotificationUserInfo(Long userId, String profileImage, String username) {
		this.userId = userId;
		this.profileImage = profileImage;
		this.username = username;
	}

	/**
	 * return value of the property profileImage
	 *
	 * @return the profileImage
	 */
	public String getProfileImage() {
		return profileImage;
	}

	/**
	 * return value of the property userId
	 *
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * return value of the property username
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * set value of the property profileImage
	 *
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	/**
	 * set value of the property userId
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * set value of the property username
	 *
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
