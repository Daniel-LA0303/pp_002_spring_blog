package com.mx.dev.blog.spring_001_blog.builders.comment;

import java.time.LocalDateTime;

import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCardDTO;

public class CommentCardDTOBuilder {

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
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of CommentCardDTOBuilder
	 */
	public static CommentCardDTOBuilder withAllDummy() {
		return new CommentCardDTOBuilder().setBlogId(null).setContent(null).setUserId(null).setCommentId(null)
				.setProfilePicture(null).setUsername(null).setUpdatedAt(null);
	}

	/**
	 * Builds a CommentCardDTO instance.
	 *
	 * @return a new instance of CommentCardDTO
	 */
	public CommentCardDTO build() {
		CommentCardDTO commentCardDTO = new CommentCardDTO();
		commentCardDTO.setCommentId(commentId);
		commentCardDTO.setBlogId(blogId);
		commentCardDTO.setContent(content);
		commentCardDTO.setUserId(userId);
		commentCardDTO.setProfilePicture(profilePicture);
		commentCardDTO.setUsername(username);
		commentCardDTO.setUpdatedAt(updatedAt);

		return commentCardDTO;
	}

	/**
	 * set the value of the proppertie blogId
	 *
	 * @param blogId the blogId to set
	 */
	public CommentCardDTOBuilder setBlogId(Long blogId) {
		this.blogId = blogId;
		return this;
	}

	/**
	 * set the value of the proppertie commentId
	 *
	 * @param commentId the commentId to set
	 */
	public CommentCardDTOBuilder setCommentId(Long commentId) {
		this.commentId = commentId;
		return this;
	}

	/**
	 * set the value of the proppertie content
	 *
	 * @param content the content to set
	 */
	public CommentCardDTOBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * set the value of the proppertie profilePicture
	 *
	 * @param profilePicture the profilePicture to set
	 */
	public CommentCardDTOBuilder setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
		return this;
	}

	/**
	 * set the value of the proppertie updatedAt
	 *
	 * @param updatedAt the updatedAt to set
	 */
	public CommentCardDTOBuilder setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	/**
	 * set the value of the proppertie userId
	 *
	 * @param userId the userId to set
	 */
	public CommentCardDTOBuilder setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * set the value of the proppertie username
	 *
	 * @param username the username to set
	 */
	public CommentCardDTOBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

}
