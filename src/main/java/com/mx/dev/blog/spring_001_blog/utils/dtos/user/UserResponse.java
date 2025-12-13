package com.mx.dev.blog.spring_001_blog.utils.dtos.user;

import java.time.LocalDateTime;

public class UserResponse {

	private Long id;

	private String username;

	private String email;

	private LocalDateTime lastSeen;

	private boolean isOnline;

	/**
	 * 
	 */
	public UserResponse() {
	}

	/**
	 * @param id
	 * @param username
	 * @param email
	 * @param lastSeen
	 * @param isOnline
	 */
	public UserResponse(Long id, String username, String email, LocalDateTime lastSeen, boolean isOnline) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.lastSeen = lastSeen;
		this.isOnline = isOnline;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getLastSeen() {
		return lastSeen;
	}

	public String getUsername() {
		return username;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
