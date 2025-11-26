package com.mx.dev.blog.spring_001_blog.builders.blog;

import java.time.LocalDateTime;

import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.BlogStatusEnum;

public class BlogResponseDTOBuilder {

	/**
	 * id
	 */
	private Long blogId;

	/**
	 * title
	 */
	private String title;

	/**
	 * description
	 */
	private String description;

	/**
	 * content
	 */
	private String content;

	/**
	 * status
	 */
	private BlogStatusEnum status;

	/**
	 * slug
	 */
	private String slug;

	/**
	 * created at
	 */
	private LocalDateTime createdAt;

	/**
	 * user id
	 */
	private Long userId;

	/**
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of BlogResponseDTO
	 */
	public static BlogResponseDTOBuilder withAllDummy() {
		return new BlogResponseDTOBuilder().setBlogId(1L).setTitle("Test Blog Title ")
				.setDescription("Dummy description for the blog response DTO.")
				.setContent("This is dummy blog content used for testing purposes.").setStatus(BlogStatusEnum.PUBLISHED)
				.setSlug("test-blog-slug-" + System.currentTimeMillis()).setCreatedAt(LocalDateTime.now())
				.setUserId(1L);
	}

	/**
	 * Builds a BlogResponseDTO instance.
	 *
	 * @return a new instance of BlogResponseDTO
	 */
	public BlogResponseDTO build() {
		BlogResponseDTO blogResponseDTO = new BlogResponseDTO();
		blogResponseDTO.setBlogId(blogId);
		blogResponseDTO.setContent(content);
		blogResponseDTO.setCreatedAt(createdAt);
		blogResponseDTO.setDescription(description);
		blogResponseDTO.setSlug(slug);
		blogResponseDTO.setStatus(status);
		blogResponseDTO.setTitle(title);
		blogResponseDTO.setUserId(userId);

		return blogResponseDTO;
	}

	/**
	 * set the value of the proppertie blogId
	 *
	 * @param blogId the blogId to set
	 */
	public BlogResponseDTOBuilder setBlogId(Long blogId) {
		this.blogId = blogId;
		return this;
	}

	/**
	 * set the value of the proppertie content
	 *
	 * @param content the content to set
	 */
	public BlogResponseDTOBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * set the value of the proppertie createdAt
	 *
	 * @param createdAt the createdAt to set
	 */
	public BlogResponseDTOBuilder setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	/**
	 * set the value of the proppertie description
	 *
	 * @param description the description to set
	 */
	public BlogResponseDTOBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * set the value of the proppertie slug
	 *
	 * @param slug the slug to set
	 */
	public BlogResponseDTOBuilder setSlug(String slug) {
		this.slug = slug;
		return this;
	}

	/**
	 * set the value of the proppertie status
	 *
	 * @param status the status to set
	 */
	public BlogResponseDTOBuilder setStatus(BlogStatusEnum status) {
		this.status = status;
		return this;
	}

	/**
	 * set the value of the proppertie title
	 *
	 * @param title the title to set
	 */
	public BlogResponseDTOBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	/**
	 * set the value of the proppertie userId
	 *
	 * @param userId the userId to set
	 */
	public BlogResponseDTOBuilder setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

}
