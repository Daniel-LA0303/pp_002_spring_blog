package com.mx.dev.blog.spring_001_blog.builders.comment;

import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCreateRequestDTO;

public class CommentCreateRequestDTOBuilder {

	private String content;

	private Long userId;

	private Long blogId;

	/**
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of CommentCreateRequestDTOBuilder
	 */
	public static CommentCreateRequestDTOBuilder withAllDummy() {
		return new CommentCreateRequestDTOBuilder().setBlogId(1L).setContent("New comment in blog with id 1")
				.setUserId(1L);
	}

	/**
	 * Builds a CommentCreateRequestDTO instance.
	 *
	 * @return a new instance of CommentCreateRequestDTO
	 */
	public CommentCreateRequestDTO build() {
		CommentCreateRequestDTO commentCreateRequestDTO = new CommentCreateRequestDTO();
		commentCreateRequestDTO.setBlogId(blogId);
		commentCreateRequestDTO.setContent(content);
		commentCreateRequestDTO.setUserId(userId);

		return commentCreateRequestDTO;
	}

	/**
	 * set the value of the proppertie blogId
	 *
	 * @param blogId the blogId to set
	 */
	public CommentCreateRequestDTOBuilder setBlogId(Long blogId) {
		this.blogId = blogId;
		return this;
	}

	/**
	 * set the value of the proppertie content
	 *
	 * @param content the content to set
	 */
	public CommentCreateRequestDTOBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * set the value of the proppertie userId
	 *
	 * @param userId the userId to set
	 */
	public CommentCreateRequestDTOBuilder setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

}
