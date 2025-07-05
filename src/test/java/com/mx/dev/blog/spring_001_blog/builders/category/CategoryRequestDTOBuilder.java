package com.mx.dev.blog.spring_001_blog.builders.category;

import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;

public class CategoryRequestDTOBuilder {

	/**
	 * Name
	 */
	private String name;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Color
	 */
	private String color;

	/**
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of CategoryRequestDTOBuilder
	 */
	public static CategoryRequestDTOBuilder withAllDummy() {
		return new CategoryRequestDTOBuilder().setName("Sample Category")
				.setDescription("This is a sample description.").setColor("#FF5733");
	}

	/**
	 * Builds a CategoryRequestDTO instance.
	 *
	 * @return a new instance of CategoryRequestDTO
	 */
	public CategoryRequestDTO build() {
		CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
		categoryRequestDTO.setName(name);
		categoryRequestDTO.setDescription(description);
		categoryRequestDTO.setColor(color);
		return categoryRequestDTO;
	}

	/**
	 * Sets the value of the 'color' property.
	 *
	 * @param color the color to set
	 * @return the current builder instance
	 */
	public CategoryRequestDTOBuilder setColor(String color) {
		this.color = color;
		return this;
	}

	/**
	 * Sets the value of the 'description' property.
	 *
	 * @param description the description to set
	 * @return the current builder instance
	 */
	public CategoryRequestDTOBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Sets the value of the 'name' property.
	 *
	 * @param name the name to set
	 * @return the current builder instance
	 */
	public CategoryRequestDTOBuilder setName(String name) {
		this.name = name;
		return this;
	}
}
