package com.mx.dev.blog.spring_001_blog.blog.utils.mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogEngagementDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogPageResponseDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.enums.BlogStatusEnum;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategorySmallInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;

public class BlogMappers {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static BlogInfoCardDTO mapRow(Object[] row) {

		BlogInfoCardDTO dto = new BlogInfoCardDTO();

		dto.setBlogId(((Number) row[0]).longValue());
		dto.setTitle((String) row[1]);
		dto.setDescription((String) row[2]);
		dto.setCreatedAt(((Timestamp) row[3]).toLocalDateTime());
		dto.setStatus(BlogStatusEnum.valueOf((String) row[4]));
		dto.setSlug((String) row[5]);

		// NEW: Owner info
		dto.setUserId(((Number) row[6]).longValue());
		dto.setUsername((String) row[7]);

		// Engagement
		BlogEngagementDTO engagement = new BlogEngagementDTO();
		engagement.setLikesNumber(((Number) row[8]).longValue());
		engagement.setCommentsNumber(((Number) row[9]).longValue());
		engagement.setSavedNumber(((Number) row[10]).longValue());
		dto.setBlogEngagementDTO(engagement);

		try {
			// JSON lists
			dto.setUsersLiked(objectMapper.readValue(row[11].toString(), new TypeReference<List<Long>>() {
			}));

			dto.setUsersReaded(objectMapper.readValue(row[12].toString(), new TypeReference<List<Long>>() {
			}));

			dto.setCategories(
					objectMapper.readValue(row[13].toString(), new TypeReference<List<CategorySmallInfoDTO>>() {
					}));

		} catch (Exception e) {
			throw new RuntimeException("Error parsing JSON", e);
		}

		return dto;
	}

	public static BlogInfoCardDTO toBlogInfoCardDTO(BlogEntity blogEntity, String username) {
		BlogInfoCardDTO dto = new BlogInfoCardDTO();
		dto.setBlogId(blogEntity.getBlogId());
		dto.setTitle(blogEntity.getTitle());
		dto.setDescription(blogEntity.getDescription());
		dto.setStatus(blogEntity.getStatus());
		dto.setSlug(blogEntity.getSlug());
		dto.setCreatedAt(blogEntity.getCreatedAt());
		dto.setUserId(blogEntity.getUserId());
		dto.setUsername(username);

		// Convertir las categorías a CategorySmallInfoDTO
		List<CategorySmallInfoDTO> categories = blogEntity.getCategories().stream()
				.map(category -> new CategorySmallInfoDTO(category.getCategoryId(), category.getName(),
						category.getDescription(), category.getColor(), category.getCreatedAt()))
				.collect(Collectors.toList());
		dto.setCategories(categories);

		return dto;
	}

	public static BlogResponseDTO toCategoryResponseDTO(BlogEntity blogEntity) {

		BlogResponseDTO blogResponseDTO = new BlogResponseDTO();
		blogResponseDTO.setBlogId(blogEntity.getBlogId());
		blogResponseDTO.setContent(blogEntity.getContent());
		blogResponseDTO.setCreatedAt(blogEntity.getCreatedAt());
		blogResponseDTO.setDescription(blogEntity.getDescription());
		blogResponseDTO.setSlug(blogEntity.getSlug());
		blogResponseDTO.setStatus(blogEntity.getStatus());
		blogResponseDTO.setTitle(blogEntity.getTitle());
		blogResponseDTO.setUserId(blogEntity.getUserId());

		return blogResponseDTO;
	}

	public static BlogEntity toCreateABlog(BlogCreateRequestDTO blogCreateRequestDTO, String slug, Long userId,
			String imageUrl) {

		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setContent(blogCreateRequestDTO.getContent());
		blogEntity.setCreatedAt(LocalDateTime.now());
		blogEntity.setDescription(blogCreateRequestDTO.getDescription());
		blogEntity.setSlug(slug);
		blogEntity.setStatus(BlogStatusEnum.PUBLISHED);
		blogEntity.setTitle(blogCreateRequestDTO.getTitle());
		blogEntity.setUpdatedAt(LocalDateTime.now());
		blogEntity.setUserId(userId);
		blogEntity.setDeleted(false);
		blogEntity.setBlogImgUrl(imageUrl);

		return blogEntity;
	}

	public static BlogEntity toCreateABlogWithoutImage(BlogCreateRequestDTO dto, String slug, Long userId) {
		BlogEntity blog = new BlogEntity();
		blog.setContent(dto.getContent());
		blog.setCreatedAt(LocalDateTime.now());
		blog.setDescription(dto.getDescription());
		blog.setSlug(slug);
		blog.setStatus(BlogStatusEnum.PUBLISHED);
		blog.setTitle(dto.getTitle());
		blog.setUpdatedAt(LocalDateTime.now());
		blog.setUserId(userId);
		blog.setDeleted(false);
		blog.setBlogImgUrl(null);
		return blog;
	}

	public static List<BlogResponseDTO> toListBlogResponseDTO(List<BlogEntity> blogEntities) {

		return blogEntities.stream().map(BlogMappers::toCategoryResponseDTO).collect(Collectors.toList());
	}

	// Método para convertir Page<BlogEntity> a Page<BlogInfoCardDTO>
	public static Page<BlogInfoCardDTO> toPageBlogInfoCardDTO(Page<BlogEntity> blogEntities,
			Map<Long, String> usernames) {
		return blogEntities.map(blogEntity -> toBlogInfoCardDTO(blogEntity, usernames.get(blogEntity.getUserId())));
	}

	public static Page<BlogResponseDTO> toPageBlogResponseDTO(Page<BlogEntity> blogEntities) {
		return blogEntities.map(BlogMappers::toCategoryResponseDTO);
	}

	public static BlogPageResponseDTO toViewBlogResponse(BlogEntity blogEntity, List<CategorySmallInfoDTO> categories,
			UserInfoCardDTO userInfoCardDTO, List<Long> usersLiked, List<Long> usersReaded,
			BlogEngagementDTO blogEngagementDTO) {
		BlogPageResponseDTO blogResponsePageDTO = new BlogPageResponseDTO();
		blogResponsePageDTO.setBlogId(blogEntity.getBlogId());
		blogResponsePageDTO.setTitle(blogEntity.getTitle());
		blogResponsePageDTO.setDescription(blogEntity.getDescription());
		blogResponsePageDTO.setContent(blogEntity.getContent());
		blogResponsePageDTO.setStatus(blogEntity.getStatus());
		blogResponsePageDTO.setSlug(blogEntity.getSlug());
		blogResponsePageDTO.setCreatedAt(blogEntity.getCreatedAt());
		blogResponsePageDTO.setCategories(categories);
		blogResponsePageDTO.setUserInfoCardDTO(userInfoCardDTO);
		blogResponsePageDTO.setBlogEngagementDTO(blogEngagementDTO);
		blogResponsePageDTO.setUsersLiked(usersLiked);
		blogResponsePageDTO.setUsersReaded(usersReaded);
		blogResponsePageDTO.setBlogImage(blogEntity.getBlogImgUrl());

		return blogResponsePageDTO;
	}

}
