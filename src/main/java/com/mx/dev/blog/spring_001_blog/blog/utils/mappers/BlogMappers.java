package com.mx.dev.blog.spring_001_blog.blog.utils.mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategorySmallInfoDTO;

public class BlogMappers {

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

}
