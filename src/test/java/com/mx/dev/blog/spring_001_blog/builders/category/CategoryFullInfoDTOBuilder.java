package com.mx.dev.blog.spring_001_blog.builders.category;

import java.time.LocalDateTime;
import java.util.List;

import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO;

public class CategoryFullInfoDTOBuilder {

	/**
	 * category id
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
	 * posts number
	 */
	private Long postsNumber;

	/**
	 * long description
	 */
	private String longDescription;

	/**
	 * users followers
	 */
	private List<Long> usersFollowersIds;

	/**
	 * created at
	 */
	private LocalDateTime createdAt;

	/**
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of CategoryFullInfoDTOBuilder
	 */
	public static CategoryFullInfoDTOBuilder withAllDummy() {
		return new CategoryFullInfoDTOBuilder().setCategoryId(3L).setName("Lifestyle")
				.setDescription("Lifestyle tips and tricks").setColor("#5733FF").setPostsNumber(5L)
				.setLongDescription("Inspiration and tips to improve daily living.")
				.setCreatedAt(LocalDateTime.now().minusDays(3));
	}

	/**
	 * Builds a CategoryFullInfoDTO instance.
	 *
	 * @return a new instance of CategoryFullInfoDTO
	 */
	public CategoryFullInfoDTO build() {
		CategoryFullInfoDTO categoryFullInfoDTO = new CategoryFullInfoDTO();
		categoryFullInfoDTO.setCategoryId(categoryId);
		categoryFullInfoDTO.setColor(color);
		categoryFullInfoDTO.setCreatedAt(createdAt);
		categoryFullInfoDTO.setDescription(description);
		categoryFullInfoDTO.setLongDescription(longDescription);
		categoryFullInfoDTO.setName(name);
		categoryFullInfoDTO.setPostsNumber(postsNumber);

		return categoryFullInfoDTO;
	}

	/**
	 * set the value of the property categoryId
	 *
	 * @param categoryId the categoryId to set
	 */
	public CategoryFullInfoDTOBuilder setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	/**
	 * set the value of the property color
	 *
	 * @param color the color to set
	 */
	public CategoryFullInfoDTOBuilder setColor(String color) {
		this.color = color;
		return this;
	}

	/**
	 * set the value of the property createdAt
	 *
	 * @param createdAt the createdAt to set
	 */
	public CategoryFullInfoDTOBuilder setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	/**
	 * set the value of the property description
	 *
	 * @param description the description to set
	 */
	public CategoryFullInfoDTOBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	/**
	 * set the value of the property longDescription
	 *
	 * @param longDescription the longDescription to set
	 */
	public CategoryFullInfoDTOBuilder setLongDescription(String longDescription) {
		this.longDescription = longDescription;
		return this;
	}

	/**
	 * set the value of the property name
	 *
	 * @param name the name to set
	 */
	public CategoryFullInfoDTOBuilder setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * set the value of the property postsNumber
	 *
	 * @param postsNumber the postsNumber to set
	 */
	public CategoryFullInfoDTOBuilder setPostsNumber(Long postsNumber) {
		this.postsNumber = postsNumber;
		return this;
	}

	/**
	 * set the value of the property usersFollowersIds
	 *
	 * @param usersFollowersIds the usersFollowersIds to set
	 */
	public CategoryFullInfoDTOBuilder setUsersFollowersIds(List<Long> usersFollowersIds) {
		this.usersFollowersIds = usersFollowersIds;
		return this;
	}

}
