package com.mx.dev.blog.spring_001_blog.utils.dtos.category;

import java.time.LocalDateTime;

public class CategoryFullInfoDTO {

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
	 * created at
	 */
	private LocalDateTime createdAt;

	/**
	 * 
	 */
	public CategoryFullInfoDTO() {
	}

	/**
	 * @param categoryId
	 * @param name
	 * @param description
	 * @param color
	 * @param postsNumber
	 * @param longDescription
	 * @param createdAt
	 */
	public CategoryFullInfoDTO(Long categoryId, String name, String description, String color, Long postsNumber,
			String longDescription, LocalDateTime createdAt) {
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.color = color;
		this.postsNumber = postsNumber;
		this.longDescription = longDescription;
		this.createdAt = createdAt;
	}

	/**
	 * return the value of the property categoryId
	 *
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * return the value of the property color
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * return the value of the property createdAt
	 *
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * return the value of the property description
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * return the value of the property longDescription
	 *
	 * @return the longDescription
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * return the value of the property nameString
	 *
	 * @return the nameString
	 */
	public String getName() {
		return name;
	}

	/**
	 * return the value of the property postsNumber
	 *
	 * @return the postsNumber
	 */
	public Long getPostsNumber() {
		return postsNumber;
	}

	/**
	 * set the value of the property categoryId
	 *
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * set the value of the property color
	 *
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * set the value of the property createdAt
	 *
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * set the value of the property description
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * set the value of the property longDescription
	 *
	 * @param longDescription the longDescription to set
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	/**
	 * set the value of the property name
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * set the value of the property nameString
	 *
	 * @param nameString the nameString to set
	 */
	public void setNameString(String name) {
		this.name = name;
	}

	/**
	 * set the value of the property postsNumber
	 *
	 * @param postsNumber the postsNumber to set
	 */
	public void setPostsNumber(Long postsNumber) {
		this.postsNumber = postsNumber;
	}

}
