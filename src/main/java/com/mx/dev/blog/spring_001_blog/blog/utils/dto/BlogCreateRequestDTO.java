package com.mx.dev.blog.spring_001_blog.blog.utils.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	 * image
	 */
	private MultipartFile blogImage;

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
	 * return value of the property blogImage
	 *
	 * @return the blogImage
	 */
	public MultipartFile getBlogImage() {
		return blogImage;
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
	 * set value of the property blogImage
	 *
	 * @param blogImage the blogImage to set
	 */
	public void setBlogImage(MultipartFile blogImage) {
		this.blogImage = blogImage;
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
