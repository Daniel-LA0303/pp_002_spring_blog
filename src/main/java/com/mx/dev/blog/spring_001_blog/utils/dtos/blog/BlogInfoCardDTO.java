package com.mx.dev.blog.spring_001_blog.utils.dtos.blog;

import java.time.LocalDateTime;
import java.util.List;

import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategorySmallInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.BlogStatusEnum;

public class BlogInfoCardDTO {

	/**
	 * blog id
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
	 * username
	 */
	private String username;

	/**
	 * categories
	 */
	private List<CategorySmallInfoDTO> categories;

	/**
	 * 
	 */
	public BlogInfoCardDTO() {
	}

	/**
	 * @param blogId
	 * @param title
	 * @param description
	 * @param status
	 * @param slug
	 * @param createdAt
	 * @param userId
	 * @param username
	 * @param categories
	 */
	public BlogInfoCardDTO(Long blogId, String title, String description, BlogStatusEnum status, String slug,
			LocalDateTime createdAt, Long userId, String username, List<CategorySmallInfoDTO> categories) {
		this.blogId = blogId;
		this.title = title;
		this.description = description;
		this.status = status;
		this.slug = slug;
		this.createdAt = createdAt;
		this.userId = userId;
		this.username = username;
		this.categories = categories;
	}

	/**
	 * return the value of the property blogId
	 *
	 * @return the blogId
	 */
	public Long getBlogId() {
		return blogId;
	}

	/**
	 * return the value of the property categories
	 *
	 * @return the categories
	 */
	public List<CategorySmallInfoDTO> getCategories() {
		return categories;
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
	 * return the value of the property slug
	 *
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * return the value of the property status
	 *
	 * @return the status
	 */
	public BlogStatusEnum getStatus() {
		return status;
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
	 * return the value of the property username
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * set the value of the property blogId
	 *
	 * @param blogId the blogId to set
	 */
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	/**
	 * set the value of the property categories
	 *
	 * @param categories the categories to set
	 */
	public void setCategories(List<CategorySmallInfoDTO> categories) {
		this.categories = categories;
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
	 * set the value of the property slug
	 *
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * set the value of the property status
	 *
	 * @param status the status to set
	 */
	public void setStatus(BlogStatusEnum status) {
		this.status = status;
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

	/**
	 * set the value of the property username
	 *
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
