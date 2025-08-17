package com.mx.dev.blog.spring_001_blog.integration.comment;

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

import com.mx.dev.blog.spring_001_blog.builders.comment.CommentCardDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.comment.CommentCreateRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.comment.entities.CommentEntity;
import com.mx.dev.blog.spring_001_blog.dto.PageDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "/import.sql")
@ActiveProfiles("test")
public class CommentServiceTest {

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

	CommentCreateRequestDTO commentCreateRequestDTOBuilder;

	CommentCardDTO commentCardDTOBuilder;

	@Test
	@Order(4)
	void createCommentSuccessTest() {

		HttpEntity<CommentCreateRequestDTO> requestEntity = new HttpEntity<>(commentCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<CommentCardDTO>> response = testRestTemplate.exchange("/api/comment",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<CommentCardDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<CommentCardDTO> apiResponse = response.getBody();
		assertEquals(201, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		CommentCardDTO commentCardDTO = apiResponse.getData();
		assertNotNull(commentCardDTO);

		// check data
		assertNotNull(commentCardDTO.getCommentId());
		assertEquals(commentCreateRequestDTOBuilder.getUserId(), commentCardDTO.getUserId());
		assertEquals(commentCreateRequestDTOBuilder.getContent(), commentCardDTO.getContent());
		assertEquals(commentCreateRequestDTOBuilder.getBlogId(), commentCardDTO.getBlogId());
		assertNotNull(commentCardDTO.getProfilePicture());
		assertNotNull(commentCardDTO.getUsername());
		assertNotNull(commentCardDTO.getUpdatedAt());

	}

	@Test
	@Order(1)
	void deleteCommentSuccessTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/comment/1?userId=1&blogId=1",
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
		assertEquals("Comment deleted successfully", apiResponse.getMessage());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(2)
	void getCommentByBlogSuccessTest() {

		ResponseEntity<ApiResponse<PageDTO<CommentCardDTO>>> response = testRestTemplate.exchange(
				"/api/comment/get-comments-by-blog/2?page=0&size=10", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<PageDTO<CommentCardDTO>>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<PageDTO<CommentCardDTO>> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(3)
	void getCommentByUseruccessTest() {

		ResponseEntity<ApiResponse<List<CommentEntity>>> response = testRestTemplate.exchange(
				"/api/comment/get-comments-by-user/2?page=0&size=5", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<List<CommentEntity>>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<List<CommentEntity>> apiResponse = response.getBody();
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

		commentCreateRequestDTOBuilder = CommentCreateRequestDTOBuilder.withAllDummy().build();

		commentCardDTOBuilder = CommentCardDTOBuilder.withAllDummy().build();

		headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	@Test
	@Order(5)
	void updateCommentSuccessTest() {

		CommentCreateRequestDTO commentCreateRequestDTO = CommentCreateRequestDTOBuilder.withAllDummy()
				.setContent("New content EDIT").setUserId(2L).setBlogId(2L).build();

		HttpEntity<CommentCreateRequestDTO> requestEntity = new HttpEntity<>(commentCreateRequestDTO, headers);

		ResponseEntity<ApiResponse<CommentCardDTO>> response = testRestTemplate.exchange("/api/comment/2",
				HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<ApiResponse<CommentCardDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<CommentCardDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.PUT, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		CommentCardDTO commentCardDTO = apiResponse.getData();
		assertNotNull(commentCardDTO);

		// check data
		assertNotNull(commentCardDTO.getCommentId());
		assertEquals(commentCreateRequestDTO.getUserId(), commentCardDTO.getUserId());
		assertEquals(commentCreateRequestDTO.getContent(), commentCardDTO.getContent());
		assertEquals(commentCreateRequestDTO.getBlogId(), commentCardDTO.getBlogId());
		assertNotNull(commentCardDTO.getProfilePicture());
		assertNotNull(commentCardDTO.getUsername());
		assertNotNull(commentCardDTO.getUpdatedAt());

	}

}
