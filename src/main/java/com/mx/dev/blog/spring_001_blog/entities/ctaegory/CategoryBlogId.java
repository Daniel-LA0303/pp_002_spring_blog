package com.mx.dev.blog.spring_001_blog.entities.ctaegory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategoryBlogId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "blog_id")
	private Long blogId;

	@Column(name = "category_id")
	private Long categoryId;

	/**
	 * 
	 */
	public CategoryBlogId() {
	}

	/**
	 * @param blogId
	 * @param categoryId
	 */
	public CategoryBlogId(Long blogId, Long categoryId) {
		this.blogId = blogId;
		this.categoryId = categoryId;
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
	 * return the value of the property categoryId
	 *
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
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
	 * set the value of the property categoryId
	 *
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
