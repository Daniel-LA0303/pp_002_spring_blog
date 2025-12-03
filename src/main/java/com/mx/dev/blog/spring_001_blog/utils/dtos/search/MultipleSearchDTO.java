package com.mx.dev.blog.spring_001_blog.utils.dtos.search;

import org.springframework.data.domain.Page;

import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.BlogsByCategoryInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;

public class MultipleSearchDTO {

	/**
	 * categories searched
	 */
	private Page<BlogsByCategoryInfoDTO> categories;

	/**
	 * blogs searched
	 */
	private Page<BlogInfoCardDTO> blogs;

	/**
	 * users searched
	 */
	private Page<UserInfoCardDTO> users;

	/**
	 * 
	 */
	public MultipleSearchDTO() {
	}

	/**
	 * return the value of the property blogs
	 *
	 * @return the blogs
	 */
	public Page<BlogInfoCardDTO> getBlogs() {
		return blogs;
	}

	/**
	 * return the value of the property categories
	 *
	 * @return the categories
	 */
	public Page<BlogsByCategoryInfoDTO> getCategories() {
		return categories;
	}

	/**
	 * return the value of the property users
	 *
	 * @return the users
	 */
	public Page<UserInfoCardDTO> getUsers() {
		return users;
	}

	/**
	 * set the value of the property blogs
	 *
	 * @param blogs the blogs to set
	 */
	public void setBlogs(Page<BlogInfoCardDTO> blogs) {
		this.blogs = blogs;
	}

	/**
	 * set the value of the property categories
	 *
	 * @param categories the categories to set
	 */
	public void setCategories(Page<BlogsByCategoryInfoDTO> categories) {
		this.categories = categories;
	}

	/**
	 * set the value of the property users
	 *
	 * @param users the users to set
	 */
	public void setUsers(Page<UserInfoCardDTO> users) {
		this.users = users;
	}

}
