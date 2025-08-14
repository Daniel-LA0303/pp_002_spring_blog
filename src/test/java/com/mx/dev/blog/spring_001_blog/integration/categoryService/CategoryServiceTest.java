package com.mx.dev.blog.spring_001_blog.integration.categoryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.mx.dev.blog.spring_001_blog.builders.category.CategoryRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.category.CategoryResponseDTOBuilder;
import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "/import.sql")
@ActiveProfiles("test")
public class CategoryServiceTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	HttpHeaders headers;

	CategoryResponseDTO categoryResponseDTO;

	CategoryRequestDTO categoryRequestDTOBuilder;

	@Test
	@Order(4)
	void createCategorySuccessTest() {

		// change data builder
		CategoryResponseDTO categoryBuilder = CategoryResponseDTOBuilder.withAllDummy().setCategoryId(21L)
				.setName("Sample Category").setDescription("This is a sample description.").setColor("#FF5733")
				.setLabel("Sample Category").setValue("Sample Category").build();

		HttpEntity<CategoryRequestDTO> requestEntity = new HttpEntity<>(categoryRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/category",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(201, response.getStatusCodeValue());

		// extract api response
		ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
		assertEquals(201, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		CategoryResponseDTO categoryResponseDTO = apiResponse.getData();
		assertNotNull(categoryResponseDTO);

		// check data
		assertNotNull(categoryResponseDTO.getCategoryId());
		assertEquals(categoryBuilder.getName(), categoryResponseDTO.getName());
		assertEquals(categoryBuilder.getDescription(), categoryResponseDTO.getDescription());
		assertEquals(categoryBuilder.getColor(), categoryResponseDTO.getColor());
		assertEquals(categoryBuilder.getLabel(), categoryResponseDTO.getLabel());
		assertEquals(categoryBuilder.getValue(), categoryResponseDTO.getValue());
		assertNotNull(categoryResponseDTO.getCreatedAt());

	}

	/**
	 * get all categories test
	 */
	@Test
	@Order(1)
	void getAllCategoriesTest() {

		ResponseEntity<ApiResponse> response = testRestTemplate.getForEntity("/api/category", ApiResponse.class);

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<List<CategoryResponseDTO>> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data from api response
		List<CategoryResponseDTO> categories = apiResponse.getData();
		assertNotNull(categories);
		assertEquals(20, categories.size());

	}

	@Test
	@Order(2)
	void getCategoriesById() {

		List<Long> ids = Arrays.asList(1L, 2L);

		HttpEntity<List<Long>> requestEntity = new HttpEntity<>(ids, headers);

		ResponseEntity<ApiResponse<List<CategoryEntity>>> response = testRestTemplate.exchange(
				"/api/category/get-categories-by-id", HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<ApiResponse<List<CategoryEntity>>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<List<CategoryEntity>> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data from api response
		List<CategoryEntity> categories = apiResponse.getData();
		assertNotNull(categories);
		assertFalse(categories.isEmpty());
		assertEquals(ids.size(), categories.size());

	}

	// TODO check first blogs testing
	/*
	 * @Test
	 * 
	 * @Order(3) void getOneCategorySuccessTest() {
	 * 
	 * ResponseEntity<ApiResponse<CategoryResponseDTO>> response =
	 * testRestTemplate.exchange("/api/category/2", HttpMethod.GET, null, new
	 * ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() { });
	 * 
	 * assertEquals(HttpStatus.OK, response.getStatusCode());
	 * 
	 * ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
	 * assertNotNull(response); assertEquals(200, response.getStatusCodeValue());
	 * 
	 * CategoryResponseDTO categoryResponseDTO = apiResponse.getData();
	 * assertNotNull(categoryResponseDTO);
	 * 
	 * assertEquals(categoryResponseDTO.getCategoryId(),
	 * categoryResponseDTO.getCategoryId());
	 * assertEquals(categoryResponseDTO.getColor(), categoryResponseDTO.getColor());
	 * assertEquals(categoryResponseDTO.getDescription(),
	 * categoryResponseDTO.getDescription());
	 * assertEquals(categoryResponseDTO.getName(), categoryResponseDTO.getName());
	 * 
	 * // check apirepsonse assertEquals(200, apiResponse.getStatus());
	 * assertNotNull(apiResponse.getPath()); assertEquals(MethodEnum.GET,
	 * apiResponse.getMethod()); assertNotNull(apiResponse.getMessage());
	 * assertEquals(false, apiResponse.getError());
	 * assertNotNull(apiResponse.getTimestamp());
	 * 
	 * }
	 */

	@BeforeEach
	void setUp() {

		categoryResponseDTO = CategoryResponseDTOBuilder.withAllDummy().build();

		categoryRequestDTOBuilder = CategoryRequestDTOBuilder.withAllDummy().build();

		headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	@Test
	@Order(5)
	void updateCategorySuccessTest() {

		CategoryResponseDTO categoryResponseDTOBuilder = CategoryResponseDTOBuilder.withAllDummy().setCategoryId(1L)
				.setName("New Category EDIT").setDescription("This is a sample description.").setColor("#FF5733")
				.setLabel("New Category EDIT").setValue("New Category EDIT").build();

		CategoryRequestDTO categoryRequestDTOBuilder = CategoryRequestDTOBuilder.withAllDummy()
				.setName("New Category EDIT").build();

		HttpEntity<CategoryRequestDTO> requestEntity = new HttpEntity<>(categoryRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/category/1",
				HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		CategoryResponseDTO categoryResponseDTO = apiResponse.getData();
		assertNotNull(categoryResponseDTO);

		// check data
		assertNotNull(categoryResponseDTO.getCategoryId());
		assertEquals(categoryResponseDTOBuilder.getName(), categoryResponseDTO.getName());
		assertEquals(categoryResponseDTOBuilder.getDescription(), categoryResponseDTO.getDescription());
		assertEquals(categoryResponseDTOBuilder.getColor(), categoryResponseDTO.getColor());
		assertEquals(categoryResponseDTOBuilder.getLabel(), categoryResponseDTO.getLabel());
		assertEquals(categoryResponseDTOBuilder.getValue(), categoryResponseDTO.getValue());
		assertNotNull(categoryResponseDTO.getCreatedAt());

	}

}
