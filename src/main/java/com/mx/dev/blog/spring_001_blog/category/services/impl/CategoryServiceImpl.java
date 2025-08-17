package com.mx.dev.blog.spring_001_blog.category.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.category.repositories.CategoryRepository;
import com.mx.dev.blog.spring_001_blog.category.services.CategoryService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.BlogsByCategoryInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
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

	@Override
	@Transactional
	public void categoryFollow(Long userId, Long categoryId) throws ServiceException {

//		try {

		if (categoryRepository.existsByUserIdAndCategoryFollowId(userId, categoryId)) {
			throw new ServiceException("The user is already following this category.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/category", MethodEnum.POST);
		}

		categoryRepository.saveFollowCategoryUser(userId, categoryId, LocalDateTime.now());

		// } catch (Exception e) {
//			throw new ServiceException("Error while following the category.",
		// ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/category",
		// MethodEnum.GET);
		// }

	}

	@Override
	@Transactional
	public void categoryUnfollow(Long userId, Long categoryId) throws ServiceException {
		if (!categoryRepository.existsByUserIdAndCategoryFollowId(userId, categoryId)) {
			throw new ServiceException("The user is not following this category.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/api/category", MethodEnum.DELETE);
		}

		categoryRepository.deleteFollowCategoryUser(userId, categoryId);
	}

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

	@Override
	public Page<BlogsByCategoryInfoDTO> getCategoriesPaginated(int page, int size) {
		// Crear el objeto Pageable para la paginación de categorías
		Pageable pageable = PageRequest.of(page, size);

		// Obtener las categorías paginadas con la información completa de la categoría
		Page<CategoryFullInfoDTO> categoryFullInfoPage = categoryRepository.findAllCategoryFullInfo(pageable);

		// Mapear las categorías paginadas a BlogsByCategoryInfoDTO
		Page<BlogsByCategoryInfoDTO> blogsByCategoryInfoPage = categoryFullInfoPage.map(categoryFullInfoDTO -> {
			BlogsByCategoryInfoDTO blogsByCategoryInfoDTO = new BlogsByCategoryInfoDTO();

			// Obtener los seguidores de la categoría
			List<UserSimpleResponseDTO> userSimpleResponseDTO = categoryRepository
					.findTopUsersByCategory(categoryFullInfoDTO.getName(), pageable);

			// Obtener los IDs de los seguidores
			List<Long> usersFollowers = categoryRepository
					.findUserFollowersIdsByCategory(categoryFullInfoDTO.getName());

			// Establecer los valores en el DTO
			blogsByCategoryInfoDTO.setCategoryFullInfoDTO(categoryFullInfoDTO);
			blogsByCategoryInfoDTO.setFollewersCategory(userSimpleResponseDTO);
			blogsByCategoryInfoDTO.setUsersFollowersIds(usersFollowers);

			return blogsByCategoryInfoDTO;
		});

		return blogsByCategoryInfoPage;
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
	public BlogsByCategoryInfoDTO getOneCategory(String categoryName) throws ServiceException {

		// CategoryEntity categoryEntity = getCategoryByIdOrThrow(categoryId);

		Pageable pageable = PageRequest.of(0, 15);
		List<UserSimpleResponseDTO> userSimpleResponseDTO = categoryRepository.findTopUsersByCategory(categoryName,
				pageable);

		CategoryFullInfoDTO categoryFullInfoDTO = categoryRepository.findCategoryFullInfoByName(categoryName);

		BlogsByCategoryInfoDTO blogsByCategoryInfoDTO = new BlogsByCategoryInfoDTO();

		List<Long> usersFollowers = categoryRepository.findUserFollowersIdsByCategory(categoryName);

		blogsByCategoryInfoDTO.setCategoryFullInfoDTO(categoryFullInfoDTO);
		blogsByCategoryInfoDTO.setFollewersCategory(userSimpleResponseDTO);
		blogsByCategoryInfoDTO.setUsersFollowersIds(usersFollowers);

		return blogsByCategoryInfoDTO;
	}

	@Override
	public Page<BlogsByCategoryInfoDTO> searchCategories(String query, int page, int size) {
		// Crear el objeto Pageable para la paginación
		Pageable pageable = PageRequest.of(page, size);

		// Obtener las categorías paginadas que coinciden con la búsqueda
		Page<CategoryFullInfoDTO> categoryFullInfoPage = categoryRepository.findByNameContainingIgnoreCase(query,
				pageable);

		// Mapear las categorías paginadas a BlogsByCategoryInfoDTO
		Page<BlogsByCategoryInfoDTO> blogsByCategoryInfoPage = categoryFullInfoPage.map(categoryFullInfoDTO -> {
			BlogsByCategoryInfoDTO blogsByCategoryInfoDTO = new BlogsByCategoryInfoDTO();

			// Obtener los seguidores de la categoría
			List<UserSimpleResponseDTO> userSimpleResponseDTO = categoryRepository
					.findTopUsersByCategory(categoryFullInfoDTO.getName(), pageable);

			// Obtener los IDs de los seguidores
			List<Long> usersFollowers = categoryRepository
					.findUserFollowersIdsByCategory(categoryFullInfoDTO.getName());

			// Establecer los valores en el DTO
			blogsByCategoryInfoDTO.setCategoryFullInfoDTO(categoryFullInfoDTO);
			blogsByCategoryInfoDTO.setFollewersCategory(userSimpleResponseDTO);
			blogsByCategoryInfoDTO.setUsersFollowersIds(usersFollowers);

			return blogsByCategoryInfoDTO;
		});

		return blogsByCategoryInfoPage;
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
		existingCategory.setLabel(categoryRequestDTO.getName());
		existingCategory.setValue(categoryRequestDTO.getName());
		existingCategory.setUpdatedAt(LocalDateTime.now());

		CategoryEntity updatedCategory = categoryRepository.save(existingCategory);

		return CategoryMappers.fromCategoryEToCategoryEntity(updatedCategory);
	}

}
