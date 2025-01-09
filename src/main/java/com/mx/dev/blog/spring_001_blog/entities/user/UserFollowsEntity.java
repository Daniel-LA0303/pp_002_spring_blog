package com.mx.dev.blog.spring_001_blog.entities.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_follows_tbl")
public class UserFollowsEntity {

	@EmbeddedId
	private UserFollowId id;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	/**
	 * 
	 */
	public UserFollowsEntity() {
	}

	/**
	 * @param id
	 * @param createdAt
	 */
	public UserFollowsEntity(UserFollowId id, LocalDateTime createdAt) {
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
	public UserFollowId getId() {
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
	public void setId(UserFollowId id) {
		this.id = id;
	}

}
