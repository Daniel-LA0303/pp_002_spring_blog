package com.mx.dev.blog.spring_001_blog.utils.mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.mx.dev.blog.spring_001_blog.entities.ctaegory.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;

public class CategoryMappers {

	public static CategoryResponseDTO fromCategoryEToCategoryEntity(CategoryEntity categoryEntity) {

		CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

		categoryResponseDTO.setCategoryId(categoryEntity.getCategoryId());
		categoryResponseDTO.setColor(categoryEntity.getColor());
		categoryResponseDTO.setCreatedAt(categoryEntity.getCreatedAt());
		categoryResponseDTO.setDescription(categoryEntity.getDescription());
		categoryResponseDTO.setLabel(categoryEntity.getLabel());
		categoryResponseDTO.setValue(categoryEntity.getValue());
		categoryResponseDTO.setName(categoryEntity.getName());

		return categoryResponseDTO;
	}

	public static CategoryEntity toCategoryEntity(CategoryRequestDTO categoryRequestDTO) {
		CategoryEntity category = new CategoryEntity();
		category.setName(categoryRequestDTO.getName());
		category.setDescription(categoryRequestDTO.getDescription());
		category.setColor(categoryRequestDTO.getColor());
		category.setCreatedAt(LocalDateTime.now());
		category.setUpdatedAt(LocalDateTime.now());

		return category;

	}

	public static List<CategoryResponseDTO> toListCategoryResponseDTO(List<CategoryEntity> categoriesEntity) {

		return categoriesEntity.stream().map(CategoryMappers::fromCategoryEToCategoryEntity)
				.collect(Collectors.toList());

	}

}
