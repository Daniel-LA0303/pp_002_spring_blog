package com.mx.dev.blog.spring_001_blog.category.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.BlogsByCategoryInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface CategoryService {

	void categoryFollow(Long userId, Long categoryId) throws ServiceException;

	void categoryUnfollow(Long userId, Long categoryId) throws ServiceException;

	CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) throws ServiceException;

	List<CategoryResponseDTO> getAllCategories();

	Page<BlogsByCategoryInfoDTO> getCategoriesPaginated(int page, int size);

	List<CategoryEntity> getListCategories(List<Long> ids) throws ServiceException;

	BlogsByCategoryInfoDTO getOneCategory(String categoryName) throws ServiceException;

	Page<BlogsByCategoryInfoDTO> searchCategories(String query, int page, int size);

	CategoryResponseDTO updateCategroy(CategoryRequestDTO categoryRequestDTO, Long categoryId) throws ServiceException;
}
