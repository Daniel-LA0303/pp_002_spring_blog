package com.mx.dev.blog.spring_001_blog.builders.reply;

import com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCreateRequestDTO;

public class ReplyCreateRequestDTOBuilder {

	private String content;

	private Long userId;

	private Long blogId;

	private Long commentId;

	/**
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of ReplyCreateRequestDTOBuilder
	 */
	public static ReplyCreateRequestDTOBuilder withAllDummy() {
		return new ReplyCreateRequestDTOBuilder().setBlogId(1L).setContent("New reply in blog").setUserId(1L)
				.setCommentId(2L);
	}

	/**
	 * Builds a CommentCreateRequestDTO instance.
	 *
	 * @return a new instance of CommentCreateRequestDTO
	 */
	public ReplyCreateRequestDTO build() {
		ReplyCreateRequestDTO replyCreateRequestDTO = new ReplyCreateRequestDTO();
		replyCreateRequestDTO.setBlogId(blogId);
		replyCreateRequestDTO.setContent(content);
		replyCreateRequestDTO.setUserId(userId);
		replyCreateRequestDTO.setCommentId(commentId);

		return replyCreateRequestDTO;
	}

	/**
	 * set the value of the proppertie blogId
	 *
	 * @param blogId the blogId to set
	 */
	public ReplyCreateRequestDTOBuilder setBlogId(Long blogId) {
		this.blogId = blogId;
		return this;
	}

	/**
	 * set the value of the proppertie commentId
	 *
	 * @param commentId the commentId to set
	 */
	public ReplyCreateRequestDTOBuilder setCommentId(Long commentId) {
		this.commentId = commentId;
		return this;
	}

	/**
	 * set the value of the proppertie content
	 *
	 * @param content the content to set
	 */
	public ReplyCreateRequestDTOBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * set the value of the proppertie userId
	 *
	 * @param userId the userId to set
	 */
	public ReplyCreateRequestDTOBuilder setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

}
