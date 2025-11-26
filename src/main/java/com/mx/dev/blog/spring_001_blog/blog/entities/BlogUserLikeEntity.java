package com.mx.dev.blog.spring_001_blog.blog.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blog_user_like_tbl")
public class BlogUserLikeEntity {

	@EmbeddedId
	private BlogUserLikeId id;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	/**
	 * 
	 */
	public BlogUserLikeEntity() {
	}

	/**
	 * @param id
	 * @param createdAt
	 */
	public BlogUserLikeEntity(BlogUserLikeId id, LocalDateTime createdAt) {
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
	public BlogUserLikeId getId() {
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
	public void setId(BlogUserLikeId id) {
		this.id = id;
	}

}
