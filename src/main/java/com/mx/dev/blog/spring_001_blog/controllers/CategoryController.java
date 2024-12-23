package com.mx.dev.blog.spring_001_blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.entities.ctaegory.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.services.CategoryService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;
import com.mx.dev.blog.spring_001_blog.utils.validators.CategoryValidator;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:5173", methods = { RequestMethod.GET, RequestMethod.POST })
public class CategoryController {

	/**
	 * service
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * validation
	 */
	private final CategoryValidator categoryValidator = new CategoryValidator();

	/**
	 * get all categories with a dto
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllCategories() {

		List<CategoryResponseDTO> categories = categoryService.getAllCategories();

		ApiResponse<List<CategoryResponseDTO>> apiResponse = new ApiResponse<>(
				ResponseStatus.SUCCESS.getHttpStatusCode(), "/api/category", MethodEnum.GET, "Success method GET",
				categories, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * <<<<<<< HEAD =======
	 * 
	 * @param ids
	 * @return
	 * @throws ServiceException
	 */
	@PostMapping("/get-categories-by-id")
	public ResponseEntity<?> getCategoriesById(@RequestBody List<Long> ids) throws ServiceException {

		List<CategoryEntity> categories = categoryService.getListCategories(ids);

		System.out.println(categories.size());

		ApiResponse<List<CategoryEntity>> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/category", MethodEnum.POST, "Success method POST", categories, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * >>>>>>> feature/LAZD-service-category get a category by id
	 * 
	 * @param categoryId
	 * @return
	 * @throws ServiceException
	 */
	@GetMapping("/{categoryId}")
	public ResponseEntity<?> getOneCategory(@PathVariable Long categoryId) throws ServiceException {

		CategoryResponseDTO categoryResponseDTO = categoryService.getOneCategory(categoryId);

		ApiResponse<CategoryResponseDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/category", MethodEnum.GET, "Success method GET", categoryResponseDTO, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * save a new category
	 * 
	 * @param categoryRequestDTO
	 * @return
	 * @throws ServiceException
	 */
	@PostMapping
	public ResponseEntity<?> saveCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) throws ServiceException {
		categoryValidator.validate(categoryRequestDTO);

		CategoryResponseDTO categoryResponseDTO = categoryService.createCategory(categoryRequestDTO);

		ApiResponse<CategoryResponseDTO> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/category", MethodEnum.POST, "Success method POST", categoryResponseDTO, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * update a new category
	 * 
	 * @param categoryId
	 * @param categoryRequestDTO
	 * @return
	 * @throws ServiceException
	 */
	@PutMapping("/{categoryId}")
	public ResponseEntity<?> updateCategory(@PathVariable Long categoryId,
			@RequestBody CategoryRequestDTO categoryRequestDTO) throws ServiceException {
		categoryValidator.validate(categoryRequestDTO);

		CategoryResponseDTO categoryResponseDTO = categoryService.updateCategroy(categoryRequestDTO, categoryId);

		ApiResponse<CategoryResponseDTO> apiResponse = new ApiResponse<>(ResponseStatus.CREATED.getHttpStatusCode(),
				"/api/category", MethodEnum.POST, "Success method POST", categoryResponseDTO, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}
