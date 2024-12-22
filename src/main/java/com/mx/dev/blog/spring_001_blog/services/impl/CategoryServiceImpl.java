package com.mx.dev.blog.spring_001_blog.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.entities.ctaegory.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.repositories.CategoryRepository;
import com.mx.dev.blog.spring_001_blog.services.CategoryService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.mappers.CategoryMappers;

/**
 * category services
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	/**
	 * repository
	 */
	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * create a new category
	 */
	@Override
	public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) throws ServiceException {

		Optional<CategoryEntity> categoryO = categoryRepository.findCategoryByName(categoryRequestDTO.getName());
		if (categoryO.isPresent()) {
			throw new ServiceException("Category is already exists.", ResponseStatus.BAD_REQUEST.getHttpStatusCode(),
					"/api/category", MethodEnum.POST);
		}

		CategoryEntity categoryEntity = categoryRepository.save(CategoryMappers.toCategoryEntity(categoryRequestDTO));

		return CategoryMappers.fromCategoryEToCategoryEntity(categoryEntity);

	}

	/**
	 * get all categories
	 */
	@Override
	public List<CategoryResponseDTO> getAllCategories() {

		return CategoryMappers.toListCategoryResponseDTO(categoryRepository.findAll());
	}

	/**
	 * get category by id, if category doesn't exists we catch in a exception
	 * 
	 * @param categoryId
	 * @return
	 * @throws ServiceException
	 */
	public CategoryEntity getCategoryByIdOrThrow(Long categoryId) throws ServiceException {

		return categoryRepository.findById(categoryId).orElseThrow(() -> new ServiceException("Category not found",
				ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/category", MethodEnum.GET));
	}

	/**
	 * <<<<<<< HEAD ======= get a list of category by id
	 */
	@Override
	public List<CategoryEntity> getListCategories(List<Long> ids) throws ServiceException {

		// 1. get categories
		List<CategoryEntity> categoryIDS = categoryRepository.findListCategories(ids);

		// 2. extract ids
		List<Long> foundIds = categoryIDS.stream().map(CategoryEntity::getCategoryId).collect(Collectors.toList());

		// 3. ids not found
		List<Long> missingIds = ids.stream().filter(id -> !foundIds.contains(id)).collect(Collectors.toList());

		// 4. exception to categories not found
		if (!missingIds.isEmpty()) {
			throw new ServiceException("The following categories were not found: " + missingIds,
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/category", MethodEnum.POST);
		}

		return categoryIDS;
	}

	/**
	 * >>>>>>> feature/LAZD-service-category get one category
	 */
	@Override
	public CategoryResponseDTO getOneCategory(Long categoryId) throws ServiceException {

		CategoryEntity categoryEntity = getCategoryByIdOrThrow(categoryId);
		return CategoryMappers.fromCategoryEToCategoryEntity(categoryEntity);
	}

	/**
	 * update a category
	 */
	@Override
	public CategoryResponseDTO updateCategroy(CategoryRequestDTO categoryRequestDTO, Long categoryId)
			throws ServiceException {

		CategoryEntity existingCategory = getCategoryByIdOrThrow(categoryId);

		if (!existingCategory.getName().equalsIgnoreCase(categoryRequestDTO.getName())) {
			boolean nameInUse = categoryRepository.existsByName(categoryRequestDTO.getName());
			if (nameInUse) {
				throw new ServiceException("Category name is already in use.",
						ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/category", MethodEnum.PUT);
			}
		}

		existingCategory.setName(categoryRequestDTO.getName());
		existingCategory.setDescription(categoryRequestDTO.getDescription());
		existingCategory.setColor(categoryRequestDTO.getColor());
		existingCategory.setUpdatedAt(LocalDateTime.now());

		CategoryEntity updatedCategory = categoryRepository.save(existingCategory);

		return CategoryMappers.fromCategoryEToCategoryEntity(updatedCategory);
	}

}
