package com.mx.dev.blog.spring_001_blog.entities.blog;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BlogUserReadId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "blog_id")
	private Long blogId;

	/**
	 * 
	 */
	public BlogUserReadId() {
	}

	/**
	 * @param userId
	 * @param blogId
	 */
	public BlogUserReadId(Long userId, Long blogId) {
		this.userId = userId;
		this.blogId = blogId;
	}

	/**
	 * return the value of the property serialversionuid
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * set the value of the property userId
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
