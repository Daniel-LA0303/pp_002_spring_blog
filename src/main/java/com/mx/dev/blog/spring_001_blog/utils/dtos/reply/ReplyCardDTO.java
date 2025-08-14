package com.mx.dev.blog.spring_001_blog.utils.dtos.reply;

import java.time.LocalDateTime;

public class ReplyCardDTO {

	/**
	 * reply id
	 */
	private Long replyId;

	/**
	 * content
	 */
	private String content;

	/**
	 * blog id
	 */
	private Long blogId;

	/**
	 * comment id
	 */
	private Long commentId;

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
	 * updated at
	 */
	private LocalDateTime updatedAt;

	/**
	 * 
	 */
	public ReplyCardDTO() {
	}

	/**
	 * @param replyId
	 * @param content
	 * @param blogId
	 * @param commentId
	 * @param userId
	 * @param username
	 * @param profilePicture
	 * @param updatedAt
	 */
	public ReplyCardDTO(Long replyId, String content, Long blogId, Long commentId, Long userId, String username,
			String profilePicture, LocalDateTime updatedAt) {
		this.replyId = replyId;
		this.content = content;
		this.blogId = blogId;
		this.commentId = commentId;
		this.userId = userId;
		this.username = username;
		this.profilePicture = profilePicture;
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
	 * return the value of the property replyId
	 *
	 * @return the replyId
	 */
	public Long getReplyId() {
		return replyId;
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
	 * set the value of the property replyId
	 *
	 * @param replyId the replyId to set
	 */
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
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
