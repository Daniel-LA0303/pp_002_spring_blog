package com.mx.dev.blog.spring_001_blog.builders.user;

import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;

public class UserCreateRequestDTOBuilder {

	private String username;

	private String email;

	private String password;

	/**
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of CategoryFullInfoDTOBuilder
	 */
	public static UserCreateRequestDTOBuilder withAllDummy() {
		return new UserCreateRequestDTOBuilder().setEmail("email100@email.com").setPassword("123456789")
				.setUsername("user1234.user");
	}

	/**
	 * Builds a UserSimpleResponseDTO instance.
	 *
	 * @return a new instance of UserSimpleResponseDTO
	 */
	public UserCreateRequestDTO build() {
		UserCreateRequestDTO userSimpleResponseDTO = new UserCreateRequestDTO();
		userSimpleResponseDTO.setPassword(password);
		userSimpleResponseDTO.setEmail(email);
		userSimpleResponseDTO.setUsername(username);

		return userSimpleResponseDTO;
	}

	/**
	 * set the value of the proppertie email
	 *
	 * @param email the email to set
	 */
	public UserCreateRequestDTOBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * set the value of the proppertie password
	 *
	 * @param password the password to set
	 */
	public UserCreateRequestDTOBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	/**
	 * set the value of the proppertie username
	 *
	 * @param username the username to set
	 */
	public UserCreateRequestDTOBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

}
