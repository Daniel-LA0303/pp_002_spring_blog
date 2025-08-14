package com.mx.dev.blog.spring_001_blog.utils.dtos.category;

public class CategoryTopInfoDTO {

	/**
	 * category id
	 */
	private Long categoryId;

	/**
	 * name
	 */
	private String name;

	/**
	 * color
	 */
	private String color;

	/**
	 * followers
	 */
	private Long followers;

	/**
	 * 
	 */
	public CategoryTopInfoDTO() {
	}

	/**
	 * @param categoryId
	 * @param name
	 * @param color
	 * @param followers
	 */
	public CategoryTopInfoDTO(Long categoryId, String name, String color, Long followers) {
		this.categoryId = categoryId;
		this.name = name;
		this.color = color;
		this.followers = followers;
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
	 * return the value of the property followers
	 *
	 * @return the followers
	 */
	public Long getFollowers() {
		return followers;
	}

	/**
	 * return the value of the property name
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * set the value of the property followers
	 *
	 * @param followers the followers to set
	 */
	public void setFollowers(Long followers) {
		this.followers = followers;
	}

	/**
	 * set the value of the property name
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
