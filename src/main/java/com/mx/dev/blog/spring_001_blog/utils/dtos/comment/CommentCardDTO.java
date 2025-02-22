package com.mx.dev.blog.spring_001_blog.utils.dtos.comment;

import java.time.LocalDateTime;

public class CommentCardDTO {

	/**
	 * comment id
	 */
	private Long commentId;

	/**
	 * user id
	 */
	private Long userId;

	/**
	 * blog id
	 */
	private Long blogId;

	/**
	 * content
	 */
	private String content;

	/**
	 * profile picture
	 */
	private String profilePicture;

	/**
	 * username
	 */
	private String username;

	/**
	 * updatedAt
	 */
	private LocalDateTime updatedAt;

	/**
	 * 
	 */
	public CommentCardDTO() {
	}

	/**
	 * @param commentId
	 * @param userId
	 * @param blogId
	 * @param content
	 * @param profilePicture
	 * @param username
	 * @param updatedAt
	 */
	public CommentCardDTO(Long commentId, Long userId, Long blogId, String content, String profilePicture,
			String username, LocalDateTime updatedAt) {
		this.commentId = commentId;
		this.userId = userId;
		this.blogId = blogId;
		this.content = content;
		this.profilePicture = profilePicture;
		this.username = username;
		this.updatedAt = updatedAt;
	}

	/**
	 * return the value of the property blogId
	 *
	 * @return the blogId
	 */
	public Long getBlogId() {
		return blogId;
	}

	/**
	 * return the value of the property commentId
	 *
	 * @return the commentId
	 */
	public Long getCommentId() {
		return commentId;
	}

	/**
	 * return the value of the property content
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
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
	 * return the value of the property updatedAt
	 *
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
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
	 * set the value of the property blogId
	 *
	 * @param blogId the blogId to set
	 */
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	/**
	 * set the value of the property commentId
	 *
	 * @param commentId the commentId to set
	 */
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	/**
	 * set the value of the property content
	 *
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * set the value of the property updatedAt
	 *
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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
