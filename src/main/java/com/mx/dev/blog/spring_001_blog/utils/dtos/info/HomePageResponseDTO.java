package com.mx.dev.blog.spring_001_blog.utils.dtos.info;

import java.util.List;

import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryTopInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserTopDTO;

public class HomePageResponseDTO {

	/**
	 * users that are top
	 */
	private List<UserTopDTO> usersTop;

	/**
	 * categories that are top
	 */
	private List<CategoryTopInfoDTO> categoriesTop;

	/**
	 * 
	 */
	public HomePageResponseDTO() {
	}

	/**
	 * return the value of the property categoriesTop
	 *
	 * @return the categoriesTop
	 */
	public List<CategoryTopInfoDTO> getCategoriesTop() {
		return categoriesTop;
	}

	/**
	 * return the value of the property usersTop
	 *
	 * @return the usersTop
	 */
	public List<UserTopDTO> getUsersTop() {
		return usersTop;
	}

	/**
	 * set the value of the property categoriesTop
	 *
	 * @param categoriesTop the categoriesTop to set
	 */
	public void setCategoriesTop(List<CategoryTopInfoDTO> categoriesTop) {
		this.categoriesTop = categoriesTop;
	}

	/**
	 * set the value of the property usersTop
	 *
	 * @param usersTop the usersTop to set
	 */
	public void setUsersTop(List<UserTopDTO> usersTop) {
		this.usersTop = usersTop;
	}

}
