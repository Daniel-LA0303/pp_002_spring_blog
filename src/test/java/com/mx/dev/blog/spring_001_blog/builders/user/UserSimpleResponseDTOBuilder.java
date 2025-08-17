package com.mx.dev.blog.spring_001_blog.builders.user;

import java.time.LocalDateTime;

import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;

public class UserSimpleResponseDTOBuilder {

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
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of CategoryFullInfoDTOBuilder
	 */
	public static UserSimpleResponseDTOBuilder withAllDummy() {
		return new UserSimpleResponseDTOBuilder().setUserId(999L).setUsername("dummyUser").setEmail("dummy@example.com")
				.setProfilePicture("https://example.com/dummy.png").setCreatedAt(LocalDateTime.now());
	}

	/**
	 * Builds a UserSimpleResponseDTO instance.
	 *
	 * @return a new instance of UserSimpleResponseDTO
	 */
	public UserSimpleResponseDTO build() {
		UserSimpleResponseDTO userSimpleResponseDTO = new UserSimpleResponseDTO();
		userSimpleResponseDTO.setCreatedAt(createdAt);
		userSimpleResponseDTO.setEmail(email);
		userSimpleResponseDTO.setProfilePicture(profilePicture);
		userSimpleResponseDTO.setUserId(userId);
		userSimpleResponseDTO.setUsername(username);

		return userSimpleResponseDTO;
	}

	/**
	 * set the value of the proppertie createdAt
	 *
	 * @param createdAt the createdAt to set
	 */
	public UserSimpleResponseDTOBuilder setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	/**
	 * set the value of the proppertie email
	 *
	 * @param email the email to set
	 */
	public UserSimpleResponseDTOBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * set the value of the proppertie profilePicture
	 *
	 * @param profilePicture the profilePicture to set
	 */
	public UserSimpleResponseDTOBuilder setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
		return this;
	}

	/**
	 * set the value of the proppertie userId
	 *
	 * @param userId the userId to set
	 */
	public UserSimpleResponseDTOBuilder setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * set the value of the proppertie username
	 *
	 * @param username the username to set
	 */
	public UserSimpleResponseDTOBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

}
