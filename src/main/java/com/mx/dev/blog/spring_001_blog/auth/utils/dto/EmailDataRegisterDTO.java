package com.mx.dev.blog.spring_001_blog.auth.utils.dto;

public class EmailDataRegisterDTO {

	private String email;

	private String name;

	private String token;

	/**
	 * 
	 */
	public EmailDataRegisterDTO() {
	}

	/**
	 * @param email
	 * @param name
	 * @param token
	 */
	public EmailDataRegisterDTO(String email, String name, String token) {
		this.email = email;
		this.name = name;
		this.token = token;
	}

	/**
	 * return value of the property email
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * return value of the property name
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * set value of the property email
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * set value of the property name
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * set value of the property token
	 *
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

}
