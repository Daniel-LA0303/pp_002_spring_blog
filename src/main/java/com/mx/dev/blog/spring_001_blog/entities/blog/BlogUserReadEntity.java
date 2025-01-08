package com.mx.dev.blog.spring_001_blog.entities.blog;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blog_user_reada_tbl")
public class BlogUserReadEntity {

	@EmbeddedId
	private BlogUserReadId id;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	/**
	 * 
	 */
	public BlogUserReadEntity() {
	}

	/**
	 * @param id
	 * @param createdAt
	 */
	public BlogUserReadEntity(BlogUserReadId id, LocalDateTime createdAt) {
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
	public BlogUserReadId getId() {
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
	public void setId(BlogUserReadId id) {
		this.id = id;
	}

}
