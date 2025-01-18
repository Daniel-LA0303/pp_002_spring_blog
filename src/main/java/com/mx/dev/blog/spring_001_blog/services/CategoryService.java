package com.mx.dev.blog.spring_001_blog.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mx.dev.blog.spring_001_blog.entities.ctaegory.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface CategoryService {

	CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) throws ServiceException;

	List<CategoryResponseDTO> getAllCategories();

	Page<CategoryFullInfoDTO> getCategoriesPaginated(int page, int size);

	List<CategoryEntity> getListCategories(List<Long> ids) throws ServiceException;

	CategoryFullInfoDTO getOneCategory(String categoryName) throws ServiceException;

	CategoryResponseDTO updateCategroy(CategoryRequestDTO categoryRequestDTO, Long categoryId) throws ServiceException;
}
