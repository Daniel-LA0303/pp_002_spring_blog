package com.mx.dev.blog.spring_001_blog.blog.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.mx.dev.blog.spring_001_blog.blog.utils.enums.BlogStatusEnum;
import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;

@Entity
@Table(name = "blog_tbl")
public class BlogEntity {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long blogId;

	/**
	 * title
	 */
	@Column(name = "title")
	private String title;

	/**
	 * description
	 */
	@Column(name = "description")
	private String description;

	/**
	 * content
	 */
	@Column(name = "content")
	private String content;

	/**
	 * status
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private BlogStatusEnum status;

	/**
	 * slug
	 */
	@Column(name = "slug")
	private String slug;

	/**
	 * created at
	 */
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	/**
	 * updated at
	 */
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	/**
	 * user id
	 */
	@Column(name = "user_id")
	private Long userId;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "blog_category_tbl", joinColumns = @JoinColumn(name = "blog_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<CategoryEntity> categories;

	public BlogEntity() {

	}

	/**
	 * @param blogId
	 * @param title
	 * @param description
	 * @param content
	 * @param status
	 * @param slug
	 * @param createdAt
	 * @param updatedAt
	 * @param userId
	 */
	public BlogEntity(Long blogId, String title, String description, String content, BlogStatusEnum status, String slug,
			LocalDateTime createdAt, LocalDateTime updatedAt, Long userId) {
		this.blogId = blogId;
		this.title = title;
		this.description = description;
		this.content = content;
		this.status = status;
		this.slug = slug;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userId = userId;
	}

	/**
	 * @param blogId
	 * @param title
	 * @param description
	 * @param content
	 * @param status
	 * @param slug
	 * @param createdAt
	 * @param updatedAt
	 * @param userId
	 * @param categories
	 */
	public BlogEntity(Long blogId, String title, String description, String content, BlogStatusEnum status, String slug,
			LocalDateTime createdAt, LocalDateTime updatedAt, Long userId, List<CategoryEntity> categories) {
		this.blogId = blogId;
		this.title = title;
		this.description = description;
		this.content = content;
		this.status = status;
		this.slug = slug;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userId = userId;
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
	public List<CategoryEntity> getCategories() {
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
	 * return the value of the property updatedAt
	 *
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
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
	public void setCategories(List<CategoryEntity> categories) {
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
	 * set the value of the property updatedAt
	 *
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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
