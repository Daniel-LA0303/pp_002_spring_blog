package com.mx.dev.blog.spring_001_blog.blog.utils.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.dev.blog.spring_001_blog.blog.utils.enums.BlogStatusEnum;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategorySmallInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;

public class BlogPageResponseDTO {

	/**
	 * id
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
	 * content
	 */
	private String content;

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
	 * list of categories
	 */
	private List<CategorySmallInfoDTO> categories;

	/**
	 * user id
	 */
	@JsonProperty("userInfo")
	private UserInfoCardDTO userInfoCardDTO;

	/**
	 * blog engagement
	 */
	@JsonProperty("blogEngagement")
	private BlogEngagementDTO blogEngagementDTO;

	/**
	 * users liked
	 */
	private List<Long> usersLiked;

	/**
	 * users saved
	 */
	private List<Long> usersReaded;

	/**
	 * blog image
	 */
	private String blogImage;

	/**
	 * 
	 */
	public BlogPageResponseDTO() {
	}

	/**
	 * return the value of the property blogEngagementDTO
	 *
	 * @return the blogEngagementDTO
	 */
	public BlogEngagementDTO getBlogEngagementDTO() {
		return blogEngagementDTO;
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
	 * return value of the property blogImage
	 *
	 * @return the blogImage
	 */
	public String getBlogImage() {
		return blogImage;
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
	 * return the value of the property content
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
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
	 * return the value of the property userInfoCardDTO
	 *
	 * @return the userInfoCardDTO
	 */
	public UserInfoCardDTO getUserInfoCardDTO() {
		return userInfoCardDTO;
	}

	/**
	 * return the value of the property usersLiked
	 *
	 * @return the usersLiked
	 */
	public List<Long> getUsersLiked() {
		return usersLiked;
	}

	/**
	 * return the value of the property usersReaded
	 *
	 * @return the usersReaded
	 */
	public List<Long> getUsersReaded() {
		return usersReaded;
	}

	/**
	 * set the value of the property blogEngagementDTO
	 *
	 * @param blogEngagementDTO the blogEngagementDTO to set
	 */
	public void setBlogEngagementDTO(BlogEngagementDTO blogEngagementDTO) {
		this.blogEngagementDTO = blogEngagementDTO;
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
	 * set value of the property blogImage
	 *
	 * @param blogImage the blogImage to set
	 */
	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
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
	 * set the value of the property content
	 *
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * set the value of the property userInfoCardDTO
	 *
	 * @param userInfoCardDTO the userInfoCardDTO to set
	 */
	public void setUserInfoCardDTO(UserInfoCardDTO userInfoCardDTO) {
		this.userInfoCardDTO = userInfoCardDTO;
	}

	/**
	 * set the value of the property usersLiked
	 *
	 * @param usersLiked the usersLiked to set
	 */
	public void setUsersLiked(List<Long> usersLiked) {
		this.usersLiked = usersLiked;
	}

	/**
	 * set the value of the property usersReaded
	 *
	 * @param usersReaded the usersReaded to set
	 */
	public void setUsersReaded(List<Long> usersReaded) {
		this.usersReaded = usersReaded;
	}

}
