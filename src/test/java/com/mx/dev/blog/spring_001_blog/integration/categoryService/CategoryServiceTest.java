package com.mx.dev.blog.spring_001_blog.integration.categoryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
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

import com.mx.dev.blog.spring_001_blog.builders.blog.BlogsByCategoryInfoDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.category.CategoryRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.category.CategoryResponseDTOBuilder;
import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.dto.PageDTO;
import com.mx.dev.blog.spring_001_blog.utils.constants.regex.CategoryRegex;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.BlogsByCategoryInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.search.MultipleSearchDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.mappers.CategoryMappers;
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

	BlogsByCategoryInfoDTO blogsByCategoryInfoDTO;

	@Test
	void categoryMappers_constructor_isPrivate_andThrows() throws Exception {
		Constructor<CategoryMappers> ctor = CategoryMappers.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(ctor.getModifiers()), "El ctor debe ser private");

		ctor.setAccessible(true);
		InvocationTargetException ex = assertThrows(InvocationTargetException.class, ctor::newInstance);

		assertTrue(ex.getTargetException() instanceof IllegalStateException);
		assertEquals("Utility class", ex.getTargetException().getMessage());
	}

	@Test
	@Order(3)
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
	void getAllCategoriesSuccessTest() {

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
	void getCategoriesByIdSuccessTest() {

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

	@Test
	@Order(5)
	void getCategoriesPaginated() {

		ResponseEntity<ApiResponse<PageDTO<BlogsByCategoryInfoDTO>>> response = testRestTemplate.exchange(
				"/api/category/pagination?page=0&size=15", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<PageDTO<BlogsByCategoryInfoDTO>>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<PageDTO<BlogsByCategoryInfoDTO>> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(6)
	void getOneCategoryWithAllInfoSuccessTest() {

		ResponseEntity<ApiResponse<BlogsByCategoryInfoDTO>> response = testRestTemplate.exchange(
				"/api/category/Lifestyle", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<BlogsByCategoryInfoDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<BlogsByCategoryInfoDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		BlogsByCategoryInfoDTO blogsByCategoryInfoDTO = apiResponse.getData();
		assertNotNull(blogsByCategoryInfoDTO);

		// extract category full info
		CategoryFullInfoDTO categoryFullInfoDTO = blogsByCategoryInfoDTO.getCategoryFullInfoDTO();

		// check userSimpleResponseDTOs
		List<UserSimpleResponseDTO> userSimpleResponseDTOs = blogsByCategoryInfoDTO.getFollewersCategory();
		assertNotNull(userSimpleResponseDTOs);
		assertEquals(1, userSimpleResponseDTOs.size());

		// check userSimpleResponseDTOs
		List<Long> usersFollowersIds = blogsByCategoryInfoDTO.getUsersFollowersIds();
		assertNotNull(usersFollowersIds);
		assertEquals(1, usersFollowersIds.size());

		// check blogsByCategoryInfoDTO
		assertEquals(blogsByCategoryInfoDTO.getCategoryFullInfoDTO().getCategoryId(),
				categoryFullInfoDTO.getCategoryId());
		assertEquals(blogsByCategoryInfoDTO.getCategoryFullInfoDTO().getColor(), categoryFullInfoDTO.getColor());
		assertNotNull(blogsByCategoryInfoDTO.getCategoryFullInfoDTO().getCreatedAt());
		assertEquals(blogsByCategoryInfoDTO.getCategoryFullInfoDTO().getDescription(),
				categoryFullInfoDTO.getDescription());
		assertEquals(blogsByCategoryInfoDTO.getCategoryFullInfoDTO().getLongDescription(),
				categoryFullInfoDTO.getLongDescription());
		assertEquals(blogsByCategoryInfoDTO.getCategoryFullInfoDTO().getName(), categoryFullInfoDTO.getName());
		assertEquals(blogsByCategoryInfoDTO.getCategoryFullInfoDTO().getPostsNumber(),
				categoryFullInfoDTO.getPostsNumber());

	}

	@Test
	@Order(7)
	void searchCategorySuccessTest() {

		ResponseEntity<ApiResponse<PageDTO<MultipleSearchDTO>>> response = testRestTemplate.exchange(
				"/api/search?query=java&page=0&size=10", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<PageDTO<MultipleSearchDTO>>>() {
				});

		// basic test
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Search by param", response.getBody().getMessage());

		// extract api response
		ApiResponse<PageDTO<MultipleSearchDTO>> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@BeforeEach
	void setUp() {

		categoryResponseDTO = CategoryResponseDTOBuilder.withAllDummy().build();

		categoryRequestDTOBuilder = CategoryRequestDTOBuilder.withAllDummy().build();

		blogsByCategoryInfoDTO = BlogsByCategoryInfoDTOBuilder.withAllDummy().build();

		headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	@Test
	@Order(4)
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

	@Test
	@Order(9)
	void userFollowCategorySuccessfully() {

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/category/2/follow?userId=1",
				HttpMethod.POST, new HttpEntity<>(null, headers),
				new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(201, response.getStatusCodeValue());

		// extract api response
		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(201, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(10)
	void userUnfollowCategorySuccessfully() {

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/category/1/unfollow?userId=1",
				HttpMethod.DELETE, new HttpEntity<>(null, headers),
				new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.DELETE, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	void utilityConstructorIsPrivateAndThrowsCategoryRegex() throws Exception {
		Constructor<CategoryRegex> ctor = CategoryRegex.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(ctor.getModifiers()), "El constructor debe ser private");

		ctor.setAccessible(true);
		InvocationTargetException ex = assertThrows(InvocationTargetException.class, ctor::newInstance);

		assertTrue(ex.getTargetException() instanceof IllegalStateException);
		assertEquals("Utility class", ex.getTargetException().getMessage());
	}

}
