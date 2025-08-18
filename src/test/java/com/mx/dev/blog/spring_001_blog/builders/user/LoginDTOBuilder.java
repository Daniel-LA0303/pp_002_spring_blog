package com.mx.dev.blog.spring_001_blog.builders.user;

import com.mx.dev.blog.spring_001_blog.utils.dtos.user.LoginDTO;

public class LoginDTOBuilder {

	private String email;

	private String password;

	/**
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of LoginDTOBuilder
	 */
	public static LoginDTOBuilder withAllDummy() {
		return new LoginDTOBuilder().setEmail("luis@example.com").setPassword("1234");
	}

	/**
	 * Builds a LoginDTO instance.
	 *
	 * @return a new instance of LoginDTO
	 */
	public LoginDTO build() {
		LoginDTO userInfoDTO = new LoginDTO();
		userInfoDTO.setEmail(email);
		userInfoDTO.setPassword(password);

		return userInfoDTO;
	}

	/**
	 * set the value of the proppertie email
	 *
	 * @param email the email to set
	 */
	public LoginDTOBuilder setEmail(String email) {
		this.email = email;
		return this;

	}

	/**
	 * set the value of the proppertie password
	 *
	 * @param password the password to set
	 */
	public LoginDTOBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

}
