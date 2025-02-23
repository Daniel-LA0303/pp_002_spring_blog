package com.mx.dev.blog.spring_001_blog.utils.dtos.user;

import java.util.List;

public class UserInfoCardDTO {

	/**
	 * user id
	 */
	private Long userId;

	/**
	 * username
	 */
	private String username;

	/**
	 * profile picture
	 */
	private String profilePicture;

	/**
	 * city
	 */
	private String city;

	/**
	 * blogs by user
	 */
	private Long blogsByUser;

	/**
	 * followers
	 */
	private Long followers;

	/*
	 * following
	 */
	private Long following;

	/**
	 * users followers
	 */
	private List<Long> usersFollowers;

	/**
	 * 
	 */
	public UserInfoCardDTO() {
	}

	/**
	 * @param userId
	 * @param username
	 * @param profilePicture
	 * @param city
	 * @param blogsByUser
	 * @param followers
	 * @param following
	 * @param usersFollowers
	 */
	public UserInfoCardDTO(Long userId, String username, String profilePicture, String city, Long blogsByUser,
			Long followers, Long following) {
		this.userId = userId;
		this.username = username;
		this.profilePicture = profilePicture;
		this.city = city;
		this.blogsByUser = blogsByUser;
		this.followers = followers;
		this.following = following;
		// this.usersFollowers = usersFollowers;
	}

	/**
	 * return the value of the property blogsByUser
	 *
	 * @return the blogsByUser
	 */
	public Long getBlogsByUser() {
		return blogsByUser;
	}

	/**
	 * return the value of the property city
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * return the value of the property followers
	 *
	 * @return the followers
	 */
	public Long getFollowers() {
		return followers;
	}

	/**
	 * return the value of the property following
	 *
	 * @return the following
	 */
	public Long getFollowing() {
		return following;
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
	 * return the value of the property usersFollowers
	 *
	 * @return the usersFollowers
	 */
	public List<Long> getUsersFollowers() {
		return usersFollowers;
	}

	/**
	 * set the value of the property blogsByUser
	 *
	 * @param blogsByUser the blogsByUser to set
	 */
	public void setBlogsByUser(Long blogsByUser) {
		this.blogsByUser = blogsByUser;
	}

	/**
	 * set the value of the property city
	 *
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * set the value of the property followers
	 *
	 * @param followers the followers to set
	 */
	public void setFollowers(Long followers) {
		this.followers = followers;
	}

	/**
	 * set the value of the property following
	 *
	 * @param following the following to set
	 */
	public void setFollowing(Long following) {
		this.following = following;
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

	/**
	 * set the value of the property usersFollowers
	 *
	 * @param usersFollowers the usersFollowers to set
	 */
	public void setUsersFollowers(List<Long> usersFollowers) {
		this.usersFollowers = usersFollowers;
	}

}
