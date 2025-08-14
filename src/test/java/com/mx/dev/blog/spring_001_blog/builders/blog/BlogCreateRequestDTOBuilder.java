package com.mx.dev.blog.spring_001_blog.builders.blog;

import java.util.List;

import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogCreateRequestDTO;

public class BlogCreateRequestDTOBuilder {

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
	 * user id
	 */
	private Long userId;

	/**
	 * categories
	 */
	private List<Long> categories;

	/**
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of BlogCreateRequestDTO
	 */
	public static BlogCreateRequestDTOBuilder withAllDummy() {
		return new BlogCreateRequestDTOBuilder().setCategories(List.of(1L, 2L))
				.setContent(
						"Dummy blog content for testing purposes. This is placeholder text to simulate real content.")
				.setDescription("Dummy description for the test blog entry.")
				.setTitle("Test Blog Title " + System.currentTimeMillis()).setUserId(1L);
	}

	/**
	 * Builds a BlogCreateRequestDTO instance.
	 *
	 * @return a new instance of BlogCreateRequestDTO
	 */
	public BlogCreateRequestDTO build() {
		BlogCreateRequestDTO blogCreateRequestDTO = new BlogCreateRequestDTO();
		blogCreateRequestDTO.setCategories(categories);
		blogCreateRequestDTO.setContent(content);
		blogCreateRequestDTO.setDescription(description);
		blogCreateRequestDTO.setTitle(title);
		blogCreateRequestDTO.setUserId(userId);

		return blogCreateRequestDTO;
	}

	/**
	 * set the value of the property categories
	 *
	 * @param categories the categories to set
	 */
	public BlogCreateRequestDTOBuilder setCategories(List<Long> categories) {
		this.categories = categories;
		return this;
	}

	/**
	 * set the value of the property content
	 *
	 * @param content the content to set
	 */
	public BlogCreateRequestDTOBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * set the value of the property description
	 *
	 * @param description the description to set
	 */
	public BlogCreateRequestDTOBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * set the value of the property title
	 *
	 * @param title the title to set
	 */
	public BlogCreateRequestDTOBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	/**
	 * set the value of the property userId
	 *
	 * @param userId the userId to set
	 */
	public BlogCreateRequestDTOBuilder setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

}
