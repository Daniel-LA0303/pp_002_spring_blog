package com.mx.dev.blog.spring_001_blog.utils.dtos.category;

import java.util.List;

import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;

public class BlogsByCategoryInfoDTO {

	/**
	 * category info
	 */
	private CategoryFullInfoDTO categoryFullInfoDTO;

	/**
	 * category followers
	 */
	private List<UserSimpleResponseDTO> follewersCategory;

	/**
	 * 
	 */
	public BlogsByCategoryInfoDTO() {
	}

	/**
	 * @param categoryFullInfoDTO
	 * @param follewersCategory
	 */
	public BlogsByCategoryInfoDTO(CategoryFullInfoDTO categoryFullInfoDTO,
			List<UserSimpleResponseDTO> follewersCategory) {
		this.categoryFullInfoDTO = categoryFullInfoDTO;
		this.follewersCategory = follewersCategory;
	}

	/**
	 * return the value of the property categoryFullInfoDTO
	 *
	 * @return the categoryFullInfoDTO
	 */
	public CategoryFullInfoDTO getCategoryFullInfoDTO() {
		return categoryFullInfoDTO;
	}

	/**
	 * return the value of the property follewersCategory
	 *
	 * @return the follewersCategory
	 */
	public List<UserSimpleResponseDTO> getFollewersCategory() {
		return follewersCategory;
	}

	/**
	 * set the value of the property categoryFullInfoDTO
	 *
	 * @param categoryFullInfoDTO the categoryFullInfoDTO to set
	 */
	public void setCategoryFullInfoDTO(CategoryFullInfoDTO categoryFullInfoDTO) {
		this.categoryFullInfoDTO = categoryFullInfoDTO;
	}

	/**
	 * set the value of the property follewersCategory
	 *
	 * @param follewersCategory the follewersCategory to set
	 */
	public void setFollewersCategory(List<UserSimpleResponseDTO> follewersCategory) {
		this.follewersCategory = follewersCategory;
	}

}
