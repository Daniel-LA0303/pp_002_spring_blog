package com.mx.dev.blog.spring_001_blog.auth.utils.dto;

public class EmailRecueratePasswordDTO {

	private String token;

	private String newPassword;

	/**
	 * 
	 */
	public EmailRecueratePasswordDTO() {
	}

	/**
	 * @param token
	 * @param newPassword
	 */
	public EmailRecueratePasswordDTO(String token, String newPassword) {
		this.token = token;
		this.newPassword = newPassword;
	}

	/**
	 * return value of the property newPassword
	 *
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
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
	 * set value of the property newPassword
	 *
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
