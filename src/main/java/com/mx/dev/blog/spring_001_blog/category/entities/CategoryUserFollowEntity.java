package com.mx.dev.blog.spring_001_blog.category.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category_user_follow_tbl")
public class CategoryUserFollowEntity {

	@EmbeddedId
	private CategoryUserFollowId id;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	/**
	 * 
	 */
	public CategoryUserFollowEntity() {
	}

	/**
	 * @param id
	 * @param createdAt
	 */
	public CategoryUserFollowEntity(CategoryUserFollowId id, LocalDateTime createdAt) {
		this.id = id;
		this.createdAt = createdAt;
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
	 * return the value of the property id
	 *
	 * @return the id
	 */
	public CategoryUserFollowId getId() {
		return id;
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
	 * set the value of the property id
	 *
	 * @param id the id to set
	 */
	public void setId(CategoryUserFollowId id) {
		this.id = id;
	}

}
