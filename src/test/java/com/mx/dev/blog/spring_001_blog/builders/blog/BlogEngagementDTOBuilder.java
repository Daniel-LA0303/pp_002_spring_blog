package com.mx.dev.blog.spring_001_blog.builders.blog;

import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogEngagementDTO;

public class BlogEngagementDTOBuilder {

	/**
	 * blogId
	 */
	private Long blogId;

	/**
	 * number likes
	 */
	private Long likesNumber;

	/**
	 * number of comments
	 */
	private Long commentsNumber;

	/**
	 * number of saved
	 */
	private Long savedNumber;

	/**
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of BlogEngagementDTOBuilder
	 */
	public static BlogEngagementDTOBuilder withAllDummy() {
		return new BlogEngagementDTOBuilder().setBlogId(1L).setCommentsNumber(null).setLikesNumber(null)
				.setSavedNumber(null);
	}

	/**
	 * Builds a BlogEngagementDTO instance.
	 *
	 * @return a new instance of BlogEngagementDTO
	 */
	public BlogEngagementDTO build() {
		BlogEngagementDTO blogResponseDTO = new BlogEngagementDTO();
		blogResponseDTO.setBlogId(blogId);
		blogResponseDTO.setCommentsNumber(commentsNumber);
		blogResponseDTO.setLikesNumber(likesNumber);
		blogResponseDTO.setSavedNumber(savedNumber);

		return blogResponseDTO;
	}

	/**
	 * set the value of the proppertie blogId
	 *
	 * @param blogId the blogId to set
	 */
	public BlogEngagementDTOBuilder setBlogId(Long blogId) {
		this.blogId = blogId;
		return this;
	}

	/**
	 * set the value of the proppertie commentsNumber
	 *
	 * @param commentsNumber the commentsNumber to set
	 */
	public BlogEngagementDTOBuilder setCommentsNumber(Long commentsNumber) {
		this.commentsNumber = commentsNumber;
		return this;
	}

	/**
	 * set the value of the proppertie likesNumber
	 *
	 * @param likesNumber the likesNumber to set
	 */
	public BlogEngagementDTOBuilder setLikesNumber(Long likesNumber) {
		this.likesNumber = likesNumber;
		return this;
	}

	/**
	 * set the value of the proppertie savedNumber
	 *
	 * @param savedNumber the savedNumber to set
	 */
	public BlogEngagementDTOBuilder setSavedNumber(Long savedNumber) {
		this.savedNumber = savedNumber;
		return this;
	}

}
