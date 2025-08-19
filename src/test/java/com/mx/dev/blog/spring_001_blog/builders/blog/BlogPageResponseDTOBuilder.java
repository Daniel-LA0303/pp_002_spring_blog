package com.mx.dev.blog.spring_001_blog.builders.blog;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.dev.blog.spring_001_blog.builders.category.CategorySmallInfoDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserInfoCardDTOBuilder;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogPageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategorySmallInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.BlogStatusEnum;

public class BlogPageResponseDTOBuilder {

	/**
	 * id
	 */
	private Long blogId;

	/**
	 * title
	 */
	private String title;

	/**
	 * description
	 */
	private String description;

	/**
	 * content
	 */
	private String content;

	/**
	 * status
	 */
	private BlogStatusEnum status;

	/**
	 * slug
	 */
	private String slug;

	/**
	 * created at
	 */
	private LocalDateTime createdAt;

	/**
	 * list of categories
	 */
	private List<CategorySmallInfoDTO> categories;

	/**
	 * user id
	 */
	@JsonProperty("userInfo")
	private UserInfoCardDTO userInfoCardDTO;

	/**
	 * blog engagement
	 */
	@JsonProperty("blogEngagement")
	private BlogEngagementDTO blogEngagementDTO;

	/**
	 * users liked
	 */
	private List<Long> usersLiked;

	/**
	 * users saved
	 */
	private List<Long> usersReaded;

	/** Crea un builder con datos reales para el blog con id=1 */
	public static BlogPageResponseDTOBuilder withAllDummy() {
		BlogPageResponseDTOBuilder builder = new BlogPageResponseDTOBuilder();

		builder.blogId = 1L;
		builder.title = "Tech Trends 2025";
		builder.description = "Latest trends in technology";
		builder.content = "Content about tech trends...";
		builder.status = BlogStatusEnum.PUBLISHED;
		builder.slug = "tech-trends-2025";
		builder.createdAt = LocalDateTime.now().minusDays(1);

		// Categorías
		CategorySmallInfoDTO techCategory = CategorySmallInfoDTOBuilder.withAllDummy().setCategroyId(1L)
				.setName("Technology").setDescription("All about tech and gadgets").setColor("#FF5733")
				.setCreatedAt(LocalDateTime.now().minusDays(10)).build();

		CategorySmallInfoDTO healthCategory = CategorySmallInfoDTOBuilder.withAllDummy().setCategroyId(2L)
				.setName("Health").setDescription("Topics related to health and wellness").setColor("#33FF57")
				.setCreatedAt(LocalDateTime.now().minusDays(15)).build();

		builder.categories = List.of(techCategory, healthCategory);

		// Usuario
		builder.userInfoCardDTO = UserInfoCardDTOBuilder.withAllDummy().setUserId(1L).setUsername("luis")
				.setProfilePicture("pic_luis.png").setCity("CDMX").setBlogsByUser(3L).setFollowers(4L).setFollowing(3L)
				.setUsersFollowers(List.of(2L, 5L, 7L)).build();

		// Engagement
		builder.blogEngagementDTO = BlogEngagementDTOBuilder.withAllDummy().setBlogId(1L).setLikesNumber(1L)
				.setCommentsNumber(1L).setSavedNumber(1L).build();

		// Likes y lecturas
		builder.usersLiked = List.of(1L);
		builder.usersReaded = List.of(1L);

		return builder;
	}

	public BlogPageResponseDTO build() {
		BlogPageResponseDTO dto = new BlogPageResponseDTO();
		dto.setBlogId(blogId);
		dto.setTitle(title);
		dto.setDescription(description);
		dto.setContent(content);
		dto.setStatus(status);
		dto.setSlug(slug);
		dto.setCreatedAt(createdAt);
		dto.setCategories(categories);
		dto.setUserInfoCardDTO(userInfoCardDTO);
		dto.setBlogEngagementDTO(blogEngagementDTO);
		dto.setUsersLiked(usersLiked);
		dto.setUsersReaded(usersReaded);
		return dto;
	}

}
