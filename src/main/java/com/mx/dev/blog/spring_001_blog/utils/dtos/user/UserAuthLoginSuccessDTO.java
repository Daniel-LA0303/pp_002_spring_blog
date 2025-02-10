package com.mx.dev.blog.spring_001_blog.utils.dtos.user;

public class UserAuthLoginSuccessDTO {

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
	 * token info
	 */
	private JWTAuthResponseDto tokenInfo;

	/**
	 * 
	 */
	public UserAuthLoginSuccessDTO() {
	}

	/**
	 * @param userId
	 * @param username
	 * @param email
	 * @param tokenInfo
	 */
	public UserAuthLoginSuccessDTO(Long userId, String username, String email, JWTAuthResponseDto tokenInfo) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.tokenInfo = tokenInfo;
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
	 * return the value of the property tokenInfo
	 *
	 * @return the tokenInfo
	 */
	public JWTAuthResponseDto getTokenInfo() {
		return tokenInfo;
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
	 * set the value of the property email
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * set the value of the property tokenInfo
	 *
	 * @param tokenInfo the tokenInfo to set
	 */
	public void setTokenInfo(JWTAuthResponseDto tokenInfo) {
		this.tokenInfo = tokenInfo;
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
