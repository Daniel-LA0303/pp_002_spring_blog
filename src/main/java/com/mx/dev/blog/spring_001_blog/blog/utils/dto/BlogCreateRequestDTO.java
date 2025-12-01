package com.mx.dev.blog.spring_001_blog.blog.utils.dto;

import java.util.List;

public class BlogCreateRequestDTO {

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
	 * 
	 */
	public BlogCreateRequestDTO() {
	}

	/**
	 * @param title
	 * @param description
	 * @param content
	 * @param slug
	 * @param userId
	 * @param categories
	 */
	public BlogCreateRequestDTO(String title, String description, String content, Long userId, List<Long> categories) {
		this.title = title;
		this.description = description;
		this.content = content;
		this.userId = userId;
		this.categories = categories;
	}

	/**
	 * return the value of the property categories
	 *
	 * @return the categories
	 */
	public List<Long> getCategories() {
		return categories;
	}

	/**
	 * return the value of the property content
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
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
	 * return the value of the property title
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * return the value of the property userId
	 *
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * set the value of the property categories
	 *
	 * @param categories the categories to set
	 */
	public void setCategories(List<Long> categories) {
		this.categories = categories;
	}

	/**
	 * set the value of the property content
	 *
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * set the value of the property title
	 *
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * set the value of the property userId
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
