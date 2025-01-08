package com.mx.dev.blog.spring_001_blog.utils.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.mx.dev.blog.spring_001_blog.entities.blog.BlogEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponseDTO;

public class BlogMappers {

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

}
