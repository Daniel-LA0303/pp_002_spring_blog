package com.mx.dev.blog.spring_001_blog.builders.category;

import java.time.LocalDateTime;

import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategorySmallInfoDTO;

public class CategorySmallInfoDTOBuilder {

	/**
	 * category id
	 */
	private Long categroyId;

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
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of CategorySmallInfoDTOBuilder
	 */
	public static CategorySmallInfoDTOBuilder withAllDummy() {
		return new CategorySmallInfoDTOBuilder().setCategroyId(3L).setName("Lifestyle")
				.setDescription("Lifestyle tips and tricks").setColor("#5733FF")
				.setCreatedAt(LocalDateTime.now().minusDays(3));
	}

	/**
	 * Builds a categorySmallInfoDTO instance.
	 *
	 * @return a new instance of categorySmallInfoDTO
	 */
	public CategorySmallInfoDTO build() {
		CategorySmallInfoDTO categorySmallInfoDTO = new CategorySmallInfoDTO();
		categorySmallInfoDTO.setCategroyId(categroyId);
		categorySmallInfoDTO.setColor(color);
		categorySmallInfoDTO.setCreatedAt(createdAt);
		categorySmallInfoDTO.setDescription(description);
		categorySmallInfoDTO.setName(name);

		return categorySmallInfoDTO;
	}

	/**
	 * set the value of the proppertie categroyId
	 *
	 * @param categroyId the categroyId to set
	 */
	public CategorySmallInfoDTOBuilder setCategroyId(Long categroyId) {
		this.categroyId = categroyId;
		return this;
	}

	/**
	 * set the value of the proppertie color
	 *
	 * @param color the color to set
	 */
	public CategorySmallInfoDTOBuilder setColor(String color) {
		this.color = color;
		return this;
	}

	/**
	 * set the value of the proppertie createdAt
	 *
	 * @param createdAt the createdAt to set
	 */
	public CategorySmallInfoDTOBuilder setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	/**
	 * set the value of the proppertie description
	 *
	 * @param description the description to set
	 */
	public CategorySmallInfoDTOBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * set the value of the proppertie name
	 *
	 * @param name the name to set
	 */
	public CategorySmallInfoDTOBuilder setName(String name) {
		this.name = name;
		return this;
	}

}
