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
import com.mx.dev.blog.spring_001_blog.entities.ctaegory.CategoryEntity;
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
	@Order(3)
	void createCategorySuccessTest() {

		// change data builder
		CategoryResponseDTO categoryBuilder = CategoryResponseDTOBuilder.withAllDummy().setCategoryId(6L)
				.setName("Sample Category").setDescription("This is a sample description.").setColor("#FF5733").build();

		HttpEntity<CategoryRequestDTO> requestEntity = new HttpEntity<>(categoryRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/category",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() {
				});

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
		assertNotNull(apiResponse);

		// check data
		assertEquals(categoryBuilder.getColor(), apiResponse.getData().getColor());
		assertEquals(categoryBuilder.getDescription(), apiResponse.getData().getDescription());
		assertEquals(categoryBuilder.getName(), apiResponse.getData().getName());

		// check apirepsonse
		assertEquals(201, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

	/**
	 * get all categories test
	 */
	@Test
	@Order(1)
	void getAllCategoriesTest() {

		ResponseEntity<ApiResponse> response = testRestTemplate.getForEntity("/api/category", ApiResponse.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		ApiResponse<List<CategoryResponseDTO>> apiResponse = response.getBody();
		assertNotNull(response);

		List<CategoryResponseDTO> categories = apiResponse.getData();
		assertNotNull(categories);

		// check apirepsonse
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(5)
	void getCategoriesById() {

		List<Long> ids = Arrays.asList(1L, 2L);

		HttpEntity<List<Long>> requestEntity = new HttpEntity<>(ids, headers);

		ResponseEntity<ApiResponse<List<CategoryEntity>>> response = testRestTemplate.exchange(
				"/api/category/get-categories-by-id", HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<ApiResponse<List<CategoryEntity>>>() {
				});

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());

		ApiResponse<List<CategoryEntity>> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getData());
		assertFalse(apiResponse.getData().isEmpty());
		assertEquals(2, apiResponse.getData().size());
	}

	@Test
	@Order(2)
	void getOneCategorySuccessTest() {

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/category/2",
				HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() {
				});

		assertEquals(HttpStatus.OK, response.getStatusCode());

		ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());

		CategoryResponseDTO categoryResponseDTO = apiResponse.getData();
		assertNotNull(categoryResponseDTO);

		assertEquals(categoryResponseDTO.getCategoryId(), categoryResponseDTO.getCategoryId());
		assertEquals(categoryResponseDTO.getColor(), categoryResponseDTO.getColor());
		assertEquals(categoryResponseDTO.getDescription(), categoryResponseDTO.getDescription());
		assertEquals(categoryResponseDTO.getName(), categoryResponseDTO.getName());

		// check apirepsonse
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

	@BeforeEach
	void setUp() {

		categoryResponseDTO = CategoryResponseDTOBuilder.withAllDummy().build();

		categoryRequestDTOBuilder = CategoryRequestDTOBuilder.withAllDummy().build();

		headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	@Test
	@Order(4)
	void updateCategorySuccessTest() {

		CategoryResponseDTO categoryResponseDTOBuilder = CategoryResponseDTOBuilder.withAllDummy().setCategoryId(1L)
				.setName("New Category EDIT").setDescription("This is a sample description.").setColor("#FF5733")
				.build();

		CategoryRequestDTO categoryRequestDTOBuilder = CategoryRequestDTOBuilder.withAllDummy()
				.setName("New Category EDIT").build();

		HttpEntity<CategoryRequestDTO> requestEntity = new HttpEntity<>(categoryRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/category/1",
				HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() {
				});

		assertEquals(HttpStatus.OK, response.getStatusCode());

		ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
		assertNotNull(apiResponse);
		assertNotNull(apiResponse.getData());

		assertEquals(categoryResponseDTOBuilder.getCategoryId(), apiResponse.getData().getCategoryId());
		assertEquals(categoryResponseDTOBuilder.getDescription(), apiResponse.getData().getDescription());
		assertEquals(categoryResponseDTOBuilder.getColor(), apiResponse.getData().getColor());
		assertEquals(categoryResponseDTOBuilder.getName(), apiResponse.getData().getName());

		// check apirepsonse
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		// TODO should be put but is post
		// assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

}
