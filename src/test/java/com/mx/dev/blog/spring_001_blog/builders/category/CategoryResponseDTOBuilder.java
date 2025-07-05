package com.mx.dev.blog.spring_001_blog.builders.category;

import java.time.LocalDateTime;

import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;

public class CategoryResponseDTOBuilder {

	/**
	 * id
	 */
	private Long categoryId;

	/**
	 * name
	 */
	private String name;

	/**
	 * description
	 */
	private String description;

	/**
	 * color
	 */
	private String color;

	/**
	 * created at
	 */
	private LocalDateTime createdAt;

	/**
	 * Static method to initialize a builder with dummy data
	 *
	 * @return a pre-configured CategoryResponseDTOBuilder instance
	 */
	public static CategoryResponseDTOBuilder withAllDummy() {
		return new CategoryResponseDTOBuilder().setCategoryId(2L).setName("Health")
				.setDescription("Topics related to health and wellness").setColor("#33FF57")
				.setCreatedAt(LocalDateTime.parse("2024-10-11T10:45:00"));
	}

	/**
	 * Builds a CategoryResponseDTO object
	 *
	 * @return a new instance of CategoryResponseDTO
	 */
	public CategoryResponseDTO build() {
		CategoryResponseDTO responseDTO = new CategoryResponseDTO();
		responseDTO.setCategoryId(categoryId);
		responseDTO.setName(name);
		responseDTO.setDescription(description);
		responseDTO.setColor(color);
		responseDTO.setCreatedAt(createdAt);
		return responseDTO;
	}

	/**
	 * Sets the categoryId value
	 *
	 * @param categoryId the category ID to set
	 * @return the current instance of CategoryResponseDTOBuilder
	 */
	public CategoryResponseDTOBuilder setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	/**
	 * Sets the color value
	 *
	 * @param color the color to set
	 * @return the current instance of CategoryResponseDTOBuilder
	 */
	public CategoryResponseDTOBuilder setColor(String color) {
		this.color = color;
		return this;
	}

	/**
	 * Sets the createdAt value
	 *
	 * @param createdAt the creation date to set
	 * @return the current instance of CategoryResponseDTOBuilder
	 */
	public CategoryResponseDTOBuilder setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	/**
	 * Sets the description value
	 *
	 * @param description the description to set
	 * @return the current instance of CategoryResponseDTOBuilder
	 */
	public CategoryResponseDTOBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Sets the name value
	 *
	 * @param name the name to set
	 * @return the current instance of CategoryResponseDTOBuilder
	 */
	public CategoryResponseDTOBuilder setName(String name) {
		this.name = name;
		return this;
	}
}