package com.mx.dev.blog.spring_001_blog.entities.ctaegory;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blog_category_tbl")
public class CategoryBlogEntity {

	@EmbeddedId
	private CategoryBlogId id;

	/**
	 * 
	 */
	public CategoryBlogEntity() {
	}

	/**
	 * @param id
	 */
	public CategoryBlogEntity(CategoryBlogId id) {
		this.id = id;
	}

	/**
	 * return the value of the property id
	 *
	 * @return the id
	 */
	public CategoryBlogId getId() {
		return id;
	}

	/**
	 * set the value of the property id
	 *
	 * @param id the id to set
	 */
	public void setId(CategoryBlogId id) {
		this.id = id;
	}

}
