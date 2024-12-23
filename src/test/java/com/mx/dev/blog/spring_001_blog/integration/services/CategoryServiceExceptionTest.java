package com.mx.dev.blog.spring_001_blog.integration.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import org.springframework.test.context.jdbc.Sql;

import com.mx.dev.blog.spring_001_blog.builders.category.CategoryRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.entities.ctaegory.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/import.sql")
@ActiveProfiles("test")
public class CategoryServiceExceptionTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	HttpHeaders headers;

	CategoryRequestDTO validData;

	CategoryRequestDTO invalidDataCategory;

	CategoryRequestDTO categoryExisting;

	@Test
	@Order(1)
	void createCategoryExistingExceptionTest() {

		HttpEntity<CategoryRequestDTO> requestEntity = new HttpEntity<>(categoryExisting, headers);

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/category",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() {
				});

		// check http status
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		// get api response
		ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
		assertNotNull(apiResponse);
		assertNull(apiResponse.getData());

		// check apirepsonse
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(3)
	void createCategoryInvalidDataExceptionTest() {

		HttpEntity<CategoryRequestDTO> requestEntity = new HttpEntity<>(invalidDataCategory, headers);

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/category",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() {
				});

		// check http status
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		// get api response
		ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
		assertNotNull(apiResponse);
		assertNotNull(apiResponse.getData());

		// check apirepsonse
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(6)
	void getCategoriesByIdException() {

		List<Long> ids = Arrays.asList(999L, 998L);

		HttpEntity<List<Long>> requestEntity = new HttpEntity<>(ids, headers);

		ResponseEntity<ApiResponse<List<CategoryEntity>>> response = testRestTemplate.exchange(
				"/api/category/get-categories-by-id", HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<ApiResponse<List<CategoryEntity>>>() {
				});

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNotNull(response.getBody());

		ApiResponse<List<CategoryEntity>> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());

	}

	@BeforeEach
	void setUp() {

		// valid data
		validData = CategoryRequestDTOBuilder.withAllDummy().build();
		// invalid data
		invalidDataCategory = CategoryRequestDTOBuilder.withAllDummy().setColor("#jklsa920").setName(
				"The rapid development of technology has enabled significant advancements across multiple fields, from artificial intelligence to personalized medicine, opening new possibilities to improve the quality of life and tackle global challenges with a more efficient and sustainable approach.")
				.setDescription(
						"In today's fast-paced world, technology is advancing at an unprecedented rate, revolutionizing industries and reshaping the way we live. From artificial intelligence and machine learning to robotics and biotechnology, these innovations are transforming everything from healthcare and education to transportation and entertainment, creating a future where possibilities are endless and new challenges arise at every corner.")
				.build();

		categoryExisting = CategoryRequestDTOBuilder.withAllDummy().setColor("#000").setName("Education")
				.setDescription("A easy description.").build();

		headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	@Test
	@Order(2)
	void updateCategoryExistingExceptionTest() {

		HttpEntity<CategoryRequestDTO> requestEntity = new HttpEntity<>(categoryExisting, headers);

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/category/1",
				HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() {
				});

		// check http status
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		// get api respopse
		ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
		assertNotNull(apiResponse);
		assertNull(apiResponse.getData());

		// check apirepsonse
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.PUT, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(4)
	void updateCategoryInvalidDataExceptionTest() {

		HttpEntity<CategoryRequestDTO> requestEntity = new HttpEntity<>(invalidDataCategory, headers);

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/category/1",
				HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() {
				});

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
		assertNotNull(apiResponse);
		assertNotNull(apiResponse.getData());

		// check apirepsonse
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(5)
	void updateCategoryNotFoundExceptionTest() {

		HttpEntity<CategoryRequestDTO> requestEntity = new HttpEntity<>(validData, headers);

		ResponseEntity<ApiResponse<CategoryResponseDTO>> response = testRestTemplate.exchange("/api/category/99999",
				HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<ApiResponse<CategoryResponseDTO>>() {
				});

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

		ApiResponse<CategoryResponseDTO> apiResponse = response.getBody();
		assertNotNull(apiResponse);
		assertNull(apiResponse.getData());

		// check apirepsonse
		assertEquals(404, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		// TODO this response should be PUT not POST
		// assertEquals(MethodEnum.POST, apiResponse.getMethod()); <------ check this
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());
	}

}
