package com.mx.dev.blog.spring_001_blog.integration.blogService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

import com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogPageResponseDTO;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.builders.blog.BlogCreateRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.dto.PageDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "/import.sql")
@ActiveProfiles("test")

public class BlogServiceExceptionTest {

	/**
	 * test rest template
	 */
	@Autowired
	private TestRestTemplate testRestTemplate;

	/**
	 * port
	 */
	@LocalServerPort
	private int port;

	/**
	 * headers
	 */
	HttpHeaders headers;

	@Test
	@Order(1)
	void blogLikedExceptionTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/1/like?userId=1",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getMessage());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(3)
	void blogReadExceptionTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/1/read?userId=1",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getMessage());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(2)
	void blogUnlikedExceptionTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/20/unlike?userId=1",
				HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getMessage());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(4)
	void blogUnreadExceptionTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/20/read?userId=1",
				HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getMessage());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(5)
	void createBlogInvalidDataExceptionTest() {

		BlogCreateRequestDTO blogCreateRequestDTOBuilder = BlogCreateRequestDTOBuilder.withAllDummy()
				.setCategories(List.of(1L, 2L)).setContent("").setDescription("").setTitle("").setUserId(8L).build();

		HttpEntity<BlogCreateRequestDTO> requestEntity = new HttpEntity<>(blogCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<BlogResponseDTO>> response = testRestTemplate.exchange("/api/blog", HttpMethod.POST,
				requestEntity, new ParameterizedTypeReference<ApiResponse<BlogResponseDTO>>() {
				});

		// basic test
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNotNull(response);
		assertEquals(400, response.getStatusCodeValue());

		// extract api response data
		ApiResponse<BlogResponseDTO> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(5)
	void deleteBlogNotFoundExceptionTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/20/10", HttpMethod.DELETE,
				requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		// extract api response
		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.DELETE, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(7)
	void getBlogsByCategoryNameExceptionTest() {

		ResponseEntity<ApiResponse<PageDTO<BlogInfoCardDTO>>> response = testRestTemplate.exchange(
				"/api/blog/Go/blogs?page=0&size=10", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<PageDTO<BlogInfoCardDTO>>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<PageDTO<BlogInfoCardDTO>> apiResponse = response.getBody();
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
	void getOneBlogNotFoundExceptionTest() {

		ResponseEntity<ApiResponse<BlogPageResponseDTO>> response = testRestTemplate.exchange("/api/blog/100",
				HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<BlogPageResponseDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(404, response.getStatusCodeValue());

		// extract api response
		ApiResponse<BlogPageResponseDTO> apiResponse = response.getBody();
		assertEquals(404, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@BeforeEach
	void setUp() {

		headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	@Test
	@Order(5)
	void updateBlogInvalidPermissionExceptionTest() {

		BlogCreateRequestDTO blogCreateRequestDTOBuilder = BlogCreateRequestDTOBuilder.withAllDummy()
				.setCategories(List.of(1L, 2L)).setContent("New content").setDescription("New description")
				.setTitle("New title").setUserId(9L).build();

		HttpEntity<BlogCreateRequestDTO> requestEntity = new HttpEntity<>(blogCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<BlogEntity>> response = testRestTemplate.exchange("/api/blog/19", HttpMethod.PUT,
				requestEntity, new ParameterizedTypeReference<ApiResponse<BlogEntity>>() {
				});

		// basic test
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNotNull(response);
		assertEquals(400, response.getStatusCodeValue());

		// extract api response data
		ApiResponse<BlogEntity> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertEquals("/api/blog", apiResponse.getPath());
		assertEquals(MethodEnum.PUT, apiResponse.getMethod());
		assertEquals("You do not have permissions to update this blog", apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

}
