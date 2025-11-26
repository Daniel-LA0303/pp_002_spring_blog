package com.mx.dev.blog.spring_001_blog.builders.user;

import java.util.List;

import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;

public class UserInfoCardDTOBuilder {

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
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of CategoryFullInfoDTOBuilder
	 */
	public static UserInfoCardDTOBuilder withAllDummy() {
		return new UserInfoCardDTOBuilder().setUserId(999L).setUsername("dummyUser")
				.setProfilePicture("https://example.com/dummy.png").setCity(null).setBlogsByUser(null)
				.setFollowers(null).setFollowing(null).setUsersFollowers(null);
	}

	/**
	 * Builds a UserSimpleResponseDTO instance.
	 *
	 * @return a new instance of UserSimpleResponseDTO
	 */
	public UserInfoCardDTO build() {
		UserInfoCardDTO userInCardDTO = new UserInfoCardDTO();
		userInCardDTO.setUserId(userId);
		userInCardDTO.setUsername(username);
		userInCardDTO.setProfilePicture(profilePicture);
		userInCardDTO.setCity(city);
		userInCardDTO.setBlogsByUser(blogsByUser);
		userInCardDTO.setFollowers(followers);
		userInCardDTO.setFollowing(following);
		userInCardDTO.setUsersFollowers(usersFollowers);

		return userInCardDTO;
	}

	/**
	 * set the value of the proppertie blogsByUser
	 *
	 * @param blogsByUser the blogsByUser to set
	 */
	public UserInfoCardDTOBuilder setBlogsByUser(Long blogsByUser) {
		this.blogsByUser = blogsByUser;
		return this;
	}

	/**
	 * set the value of the proppertie city
	 *
	 * @param city the city to set
	 */
	public UserInfoCardDTOBuilder setCity(String city) {
		this.city = city;
		return this;
	}

	/**
	 * set the value of the proppertie followers
	 *
	 * @param followers the followers to set
	 */
	public UserInfoCardDTOBuilder setFollowers(Long followers) {
		this.followers = followers;
		return this;
	}

	/**
	 * set the value of the proppertie following
	 *
	 * @param following the following to set
	 */
	public UserInfoCardDTOBuilder setFollowing(Long following) {
		this.following = following;
		return this;
	}

	/**
	 * set the value of the proppertie profilePicture
	 *
	 * @param profilePicture the profilePicture to set
	 */
	public UserInfoCardDTOBuilder setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
		return this;
	}

	/**
	 * set the value of the proppertie userId
	 *
	 * @param userId the userId to set
	 */
	public UserInfoCardDTOBuilder setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * set the value of the proppertie username
	 *
	 * @param username the username to set
	 */
	public UserInfoCardDTOBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	/**
	 * set the value of the proppertie usersFollowers
	 *
	 * @param usersFollowers the usersFollowers to set
	 */
	public UserInfoCardDTOBuilder setUsersFollowers(List<Long> usersFollowers) {
		this.usersFollowers = usersFollowers;
		return this;
	}

}
