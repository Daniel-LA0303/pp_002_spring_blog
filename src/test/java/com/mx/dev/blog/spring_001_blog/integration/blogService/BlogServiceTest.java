package com.mx.dev.blog.spring_001_blog.integration.blogService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
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
import com.mx.dev.blog.spring_001_blog.builders.blog.BlogCreateRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.blog.BlogPageResponseDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.blog.BlogResponseDTOBuilder;
import com.mx.dev.blog.spring_001_blog.dto.PageDTO;
import com.mx.dev.blog.spring_001_blog.utils.constants.regex.BlogRegex;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogEngagementDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogPageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.blog.BlogResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.info.HomePageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.BlogStatusEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.mappers.BlogMappers;
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
	void blogLikedSuccessTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/20/like?userId=1",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(201, response.getStatusCodeValue());

		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(201, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getMessage());
		assertEquals("Success", apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(5)
	void blogReadSuccessTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/20/read?userId=1",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(201, response.getStatusCodeValue());

		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(201, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getMessage());
		assertEquals("Success", apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(4)
	void blogUnLikedSuccessTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/20/unlike?userId=2",
				HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
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
		assertNotNull(apiResponse.getMessage());
		assertEquals("Success", apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(5)
	void blogUnReadSuccessTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/20/read?userId=2",
				HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
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
		assertNotNull(apiResponse.getMessage());
		assertEquals("Success", apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(5)
	void createBlogInvalidDataExceptionTest() {

		BlogCreateRequestDTO blogCreateRequestDTOBuilder = BlogCreateRequestDTOBuilder.withAllDummy()
				.setCategories(List.of(1L, 2L)).setContent("new content").setDescription("new description")
				.setTitle("new title").setUserId(8L).build();

		HttpEntity<BlogCreateRequestDTO> requestEntity = new HttpEntity<>(blogCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<BlogEntity>> response = testRestTemplate.exchange("/api/blog", HttpMethod.POST,
				requestEntity, new ParameterizedTypeReference<ApiResponse<BlogEntity>>() {
				});

		// basic test
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());

		// extract api response data
		ApiResponse<BlogEntity> apiResponse = response.getBody();
		assertEquals(201, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

		BlogEntity blogEntityResponse = apiResponse.getData();

		assertNotNull(blogEntityResponse.getBlogId());
		assertEquals(blogCreateRequestDTOBuilder.getContent(), blogEntityResponse.getContent());
		assertEquals(blogCreateRequestDTOBuilder.getDescription(), blogEntityResponse.getDescription());
		assertEquals(blogCreateRequestDTOBuilder.getTitle(), blogEntityResponse.getTitle());
		assertEquals(blogCreateRequestDTOBuilder.getCategories().size(), blogEntityResponse.getCategories().size());
		assertEquals(blogCreateRequestDTOBuilder.getUserId(), blogEntityResponse.getUserId());
		assertNotNull(blogEntityResponse.getCreatedAt());
		assertNotNull(blogEntityResponse.getSlug());
		assertNotNull(blogEntityResponse.getUpdatedAt());
		assertEquals(BlogStatusEnum.PUBLISHED, blogEntityResponse.getStatus());

	}

	@Test
	@Order(2)
	void deleteBlogSuccessTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/blog/20/9", HttpMethod.DELETE,
				requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
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
		assertEquals("Success method DELETED", apiResponse.getMessage());
		assertEquals("Blog deleted.", apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	/**
	 * get all categories test
	 */
	@Test
	@Order(1)
	void getAllBlogsSuccessTest() {

		ResponseEntity<ApiResponse> response = testRestTemplate.getForEntity("/api/blog", ApiResponse.class);

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<List<BlogResponseDTO>> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		List<BlogResponseDTO> blogs = apiResponse.getData();
		assertNotNull(blogs);
		assertEquals(20, blogs.size());

	}

	@Test
	@Order(6)
	void getBlogPaginatedSuccessTest() {

		ResponseEntity<ApiResponse<PageDTO<BlogInfoCardDTO>>> response = testRestTemplate.exchange(
				"/api/blog/pagination?page=0&size=15", HttpMethod.GET, null,
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
	void getBlogsByCategoryNameSuccessTest() {

		ResponseEntity<ApiResponse<PageDTO<BlogInfoCardDTO>>> response = testRestTemplate.exchange(
				"/api/blog/Technology/blogs?page=0&size=10", HttpMethod.GET, null,
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
	@Order(7)
	void getBlogsPaginatedByUserSuccessTest() {

		ResponseEntity<ApiResponse<PageDTO<BlogInfoCardDTO>>> response = testRestTemplate.exchange(
				"/api/blog/pagination-by-user?userId=1&page=0&size=10", HttpMethod.GET, null,
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
	@Order(8)
	void getHomePageInfoSuccessTest() {

		ResponseEntity<ApiResponse<HomePageResponseDTO>> response = testRestTemplate.exchange(
				"/api/blog/home-page-info", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<HomePageResponseDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<HomePageResponseDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(9)
	void getOneBlogSuccessTest() {

		BlogPageResponseDTO builder = new BlogPageResponseDTOBuilder().withAllDummy().build();

		ResponseEntity<ApiResponse<BlogPageResponseDTO>> response = testRestTemplate.exchange("/api/blog/1",
				HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<BlogPageResponseDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<BlogPageResponseDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// check data blog
		BlogPageResponseDTO blogPageResponseDTO = apiResponse.getData();

		assertNotNull(blogPageResponseDTO.getBlogId());
		assertEquals(builder.getTitle(), blogPageResponseDTO.getTitle());
		assertEquals(builder.getDescription(), blogPageResponseDTO.getDescription());
		assertEquals(builder.getContent(), blogPageResponseDTO.getContent());
		assertEquals(builder.getStatus(), blogPageResponseDTO.getStatus());
		assertNotNull(blogPageResponseDTO.getSlug());
		assertNotNull(blogPageResponseDTO.getCreatedAt());
		assertEquals(builder.getCategories().size(), blogPageResponseDTO.getCategories().size());
		assertEquals(builder.getUsersLiked().size(), blogPageResponseDTO.getUsersLiked().size());
		assertEquals(builder.getUsersReaded().size(), blogPageResponseDTO.getUsersLiked().size());

		// check user data
		UserInfoCardDTO userInfoCardDTO = blogPageResponseDTO.getUserInfoCardDTO();
		assertEquals(builder.getUserInfoCardDTO().getUserId(), userInfoCardDTO.getUserId());
		assertEquals(builder.getUserInfoCardDTO().getUsername(), userInfoCardDTO.getUsername());
		assertEquals(builder.getUserInfoCardDTO().getProfilePicture(), userInfoCardDTO.getProfilePicture());
		assertEquals(builder.getUserInfoCardDTO().getCity(), userInfoCardDTO.getCity());
		assertEquals(builder.getUserInfoCardDTO().getBlogsByUser(), userInfoCardDTO.getBlogsByUser());
		assertEquals(builder.getUserInfoCardDTO().getFollowers(), userInfoCardDTO.getFollowers());
		assertEquals(builder.getUserInfoCardDTO().getFollowing(), userInfoCardDTO.getFollowing());

		// check blog engagement
		BlogEngagementDTO blogEngagementDTO = blogPageResponseDTO.getBlogEngagementDTO();
		assertEquals(builder.getBlogEngagementDTO().getBlogId(), blogEngagementDTO.getBlogId());
		assertEquals(builder.getBlogEngagementDTO().getLikesNumber(), blogEngagementDTO.getLikesNumber());
		assertEquals(builder.getBlogEngagementDTO().getCommentsNumber(), blogEngagementDTO.getCommentsNumber());
		assertEquals(builder.getBlogEngagementDTO().getSavedNumber(), blogEngagementDTO.getSavedNumber());

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
				.setTitle("New title").setUserId(8L).build();

		HttpEntity<BlogCreateRequestDTO> requestEntity = new HttpEntity<>(blogCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<BlogEntity>> response = testRestTemplate.exchange("/api/blog/19", HttpMethod.PUT,
				requestEntity, new ParameterizedTypeReference<ApiResponse<BlogEntity>>() {
				});

		// basic test
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());

		// extract api response data
		ApiResponse<BlogEntity> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertEquals("/api/blog", apiResponse.getPath());
		assertEquals(MethodEnum.PUT, apiResponse.getMethod());
		assertEquals("Success method PUT", apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		BlogEntity blogResponseDTO = apiResponse.getData();
		assertNotNull(blogResponseDTO);

		// check data assertNotNull(blogResponseDTO.getBlogId());
		assertEquals(blogCreateRequestDTOBuilder.getContent(), blogResponseDTO.getContent());
		assertNotNull(blogResponseDTO.getCreatedAt());
		assertEquals(blogCreateRequestDTOBuilder.getDescription(), blogResponseDTO.getDescription());
		assertNotNull(blogResponseDTO.getSlug());
		assertEquals(BlogStatusEnum.PUBLISHED, blogResponseDTO.getStatus());
		assertEquals(blogCreateRequestDTOBuilder.getTitle(), blogResponseDTO.getTitle());
		assertEquals(blogCreateRequestDTOBuilder.getUserId(), blogResponseDTO.getUserId());

	}

	@Test
	void utilityConstructor_isPrivate_andThrows() throws Exception {
		Constructor<BlogMappers> ctor = BlogMappers.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(ctor.getModifiers()), "El ctor debe ser private");

		ctor.setAccessible(true); // forzamos acceso
		InvocationTargetException ex = assertThrows(InvocationTargetException.class, ctor::newInstance);

		assertTrue(ex.getTargetException() instanceof IllegalStateException);
		assertEquals("Utility class", ex.getTargetException().getMessage());
	}

	// TODO check first image service

	@Test
	void utilityConstructorIsPrivateAndThrowsBlogRegex() throws Exception {
		Constructor<BlogRegex> ctor = BlogRegex.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(ctor.getModifiers()), "El constructor debe ser private");

		ctor.setAccessible(true);
		InvocationTargetException ex = assertThrows(InvocationTargetException.class, ctor::newInstance);

		assertTrue(ex.getTargetException() instanceof IllegalStateException);
		assertEquals("Utility class", ex.getTargetException().getMessage());
	}

}
