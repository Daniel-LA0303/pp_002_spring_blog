package com.mx.dev.blog.spring_001_blog.integration.blogService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import com.mx.dev.blog.spring_001_blog.builders.blog.BlogCreateRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.blog.BlogResponseDTOBuilder;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.BlogStatusEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "/import.sql")
@ActiveProfiles("test")
public class BlogServiceTest {

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

	/**
	 * request blog
	 */
	BlogCreateRequestDTO blogCreateRequestDTOBuilder;

	/**
	 * response blog
	 */
	BlogResponseDTO blogResponseDTOBuilder;

	@Test
	@Order(3)
	void createBlogSuccessTest() {

		BlogCreateRequestDTO blogCreateRequestDTOBuilder = BlogCreateRequestDTOBuilder.withAllDummy()
				.setCategories(List.of(1L, 2L)).setContent("New content").setDescription("New description")
				.setTitle("New title").setUserId(1l).build();

		HttpEntity<BlogCreateRequestDTO> requestEntity = new HttpEntity<>(blogCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<BlogResponseDTO>> response = testRestTemplate.exchange("/api/blog", HttpMethod.POST,
				requestEntity, new ParameterizedTypeReference<ApiResponse<BlogResponseDTO>>() {
				});

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());

		// extract api response data
		ApiResponse<BlogResponseDTO> apiResponse = response.getBody();
		assertEquals(201, apiResponse.getStatus());
		assertEquals("/api/blog", apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());

		assertEquals("Success method POST", apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		BlogResponseDTO blogResponseDTO = apiResponse.getData();
		assertNotNull(blogResponseDTO);

		// check data
		assertNotNull(blogResponseDTO.getBlogId());
		assertEquals(blogCreateRequestDTOBuilder.getContent(), blogResponseDTO.getContent());
		assertNotNull(blogResponseDTO.getCreatedAt());
		assertEquals(blogCreateRequestDTOBuilder.getDescription(), blogResponseDTO.getDescription());
		assertNotNull(blogResponseDTO.getSlug());
		assertEquals(BlogStatusEnum.PUBLISHED, blogResponseDTO.getStatus());
		assertEquals(blogCreateRequestDTOBuilder.getTitle(), blogResponseDTO.getTitle());
		assertEquals(blogCreateRequestDTOBuilder.getUserId(), blogResponseDTO.getUserId());

	}

	@Test
	@Order(4)
	void deleteBlogSuccessTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/10/3", HttpMethod.DELETE,
				requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		assertEquals(HttpStatus.OK, response.getStatusCode());

		ApiResponse<String> apiResponse = response.getBody();
		assertNotNull(apiResponse);
		assertNotNull(apiResponse.getData());

		// check apirepsonse
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertEquals("Success method DELETED", apiResponse.getMessage());
		assertEquals("Blog deleted.", apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	/**
	 * get all categories test
	 */
	@Test
	@Order(1)
	void getAllBlogs() {

		ResponseEntity<ApiResponse> response = testRestTemplate.getForEntity("/api/blog", ApiResponse.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		ApiResponse<List<BlogResponseDTO>> apiResponse = response.getBody();
		assertNotNull(response);

		List<BlogResponseDTO> categories = apiResponse.getData();
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
	@Order(2)
	void getOneBlogSuccessTest() {

		ResponseEntity<ApiResponse<BlogResponseDTO>> response = testRestTemplate.exchange("/api/blog/3", HttpMethod.GET,
				null, new ParameterizedTypeReference<ApiResponse<BlogResponseDTO>>() {
				});

		assertEquals(HttpStatus.OK, response.getStatusCode());

		ApiResponse<BlogResponseDTO> apiResponse = response.getBody();
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());

		BlogResponseDTO blogResponseDTO = apiResponse.getData();
		assertNotNull(blogResponseDTO);

		assertEquals(blogResponseDTOBuilder.getBlogId(), blogResponseDTO.getBlogId()); //
		assertEquals(blogResponseDTOBuilder.getDescription(), blogResponseDTO.getDescription());

		// check apirepsonse //
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

	}

	@BeforeEach
	void setUp() {

		blogCreateRequestDTOBuilder = BlogCreateRequestDTOBuilder.withAllDummy().build();

		blogResponseDTOBuilder = BlogResponseDTOBuilder.withAllDummy().build();

		headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	@Test
	@Order(5)
	void updateBlogSuccessTest() {

		BlogCreateRequestDTO blogCreateRequestDTOBuilder = BlogCreateRequestDTOBuilder.withAllDummy()
				.setCategories(List.of(1L, 2L)).setContent("New content").setDescription("New description")
				.setTitle("New title").setUserId(2l).build();

		HttpEntity<BlogCreateRequestDTO> requestEntity = new HttpEntity<>(blogCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<BlogResponseDTO>> response = testRestTemplate.exchange("/api/blog/9", HttpMethod.PUT,
				requestEntity, new ParameterizedTypeReference<ApiResponse<BlogResponseDTO>>() {
				});

		System.out.println("****************");
		System.out.println(response.getBody().getMessage());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());

		// extract api response data
		ApiResponse<BlogResponseDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertEquals("/api/blog", apiResponse.getPath());
		assertEquals(MethodEnum.PUT, apiResponse.getMethod());
		assertEquals("Success method PUT", apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		BlogResponseDTO blogResponseDTO = apiResponse.getData();
		assertNotNull(blogResponseDTO);

		// check data
		assertNotNull(blogResponseDTO.getBlogId());
		assertEquals(blogCreateRequestDTOBuilder.getContent(), blogResponseDTO.getContent());
		assertNotNull(blogResponseDTO.getCreatedAt());
		assertEquals(blogCreateRequestDTOBuilder.getDescription(), blogResponseDTO.getDescription());
		assertNotNull(blogResponseDTO.getSlug());
		assertEquals(BlogStatusEnum.DRAFT, blogResponseDTO.getStatus());
		assertEquals(blogCreateRequestDTOBuilder.getTitle(), blogResponseDTO.getTitle());
		assertEquals(blogCreateRequestDTOBuilder.getUserId(), blogResponseDTO.getUserId());

	}

}
