package com.mx.dev.blog.spring_001_blog.builders.blog;

import java.time.LocalDateTime;
import java.util.List;

import com.mx.dev.blog.spring_001_blog.builders.UserSimpleResponseDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.category.CategoryFullInfoDTOBuilder;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.BlogsByCategoryInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;

public class BlogsByCategoryInfoDTOBuilder {

	/**
	 * category info
	 */
	private CategoryFullInfoDTO categoryFullInfoDTO;

	/**
	 * category followers
	 */
	private List<UserSimpleResponseDTO> follewersCategory;

	/**
	 * users followers
	 */
	private List<Long> usersFollowersIds;

	public static BlogsByCategoryInfoDTOBuilder withAllDummy() {
		BlogsByCategoryInfoDTOBuilder builder = new BlogsByCategoryInfoDTOBuilder();

		// Usar el builder de Category
		builder.categoryFullInfoDTO = CategoryFullInfoDTOBuilder.withAllDummy().build();

		//
		builder.follewersCategory = List.of(
				UserSimpleResponseDTOBuilder.withAllDummy().setUserId(2L).setUsername("ana").setEmail("ana@example.com")
						.setProfilePicture("pic_ana.png").setCreatedAt(LocalDateTime.now()).build());

		builder.usersFollowersIds = List.of(2L);

		return builder;
	}

	public BlogsByCategoryInfoDTO build() {
		BlogsByCategoryInfoDTO dto = new BlogsByCategoryInfoDTO();
		dto.setCategoryFullInfoDTO(categoryFullInfoDTO);
		dto.setFollewersCategory(follewersCategory);
		dto.setUsersFollowersIds(usersFollowersIds);
		return dto;
	}

	public BlogsByCategoryInfoDTOBuilder setCategoryFullInfoDTO(CategoryFullInfoDTO categoryFullInfoDTO) {
		this.categoryFullInfoDTO = categoryFullInfoDTO;
		return this;
	}

	public BlogsByCategoryInfoDTOBuilder setFollewersCategory(List<UserSimpleResponseDTO> follewersCategory) {
		this.follewersCategory = follewersCategory;
		return this;
	}

	public BlogsByCategoryInfoDTOBuilder setUsersFollowersIds(List<Long> usersFollowersIds) {
		this.usersFollowersIds = usersFollowersIds;
		return this;
	}

}
