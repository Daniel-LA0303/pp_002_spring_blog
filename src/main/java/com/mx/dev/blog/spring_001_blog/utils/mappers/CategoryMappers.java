package com.mx.dev.blog.spring_001_blog.utils.mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategorySmallInfoDTO;

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

	public static CategorySmallInfoDTO toCategorySmallInfo(CategoryEntity categoryEntity) {

		CategorySmallInfoDTO categorySmallInfoDTO = new CategorySmallInfoDTO();
		categorySmallInfoDTO.setCategroyId(categoryEntity.getCategoryId());
		categorySmallInfoDTO.setColor(categoryEntity.getColor());
		categorySmallInfoDTO.setCreatedAt(categoryEntity.getCreatedAt());
		categorySmallInfoDTO.setDescription(categoryEntity.getDescription());
		categorySmallInfoDTO.setName(categoryEntity.getName());

		return categorySmallInfoDTO;

	}

	public static List<CategoryResponseDTO> toListCategoryResponseDTO(List<CategoryEntity> categoriesEntity) {

		return categoriesEntity.stream().map(CategoryMappers::fromCategoryEToCategoryEntity)
				.collect(Collectors.toList());

	}

	public static List<CategorySmallInfoDTO> toListCategorySmallInfo(List<CategoryEntity> categoriesEntity) {

		return categoriesEntity.stream().map(CategoryMappers::toCategorySmallInfo).collect(Collectors.toList());

	}

}
