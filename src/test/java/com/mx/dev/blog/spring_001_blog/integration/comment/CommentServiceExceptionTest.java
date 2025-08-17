package com.mx.dev.blog.spring_001_blog.integration.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

import com.mx.dev.blog.spring_001_blog.builders.comment.CommentCreateRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.comment.CommentCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "/import.sql")
@ActiveProfiles("test")
public class CommentServiceExceptionTest {

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
	void deleteCommentExceptionTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/comment/2?userId=3&blogId=1",
				HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
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
		assertEquals("You do not have permissions to delete this comment", apiResponse.getMessage());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(1)
	void deleteNotFoundCommentExceptionTest() {

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/comment/200?userId=3&blogId=1",
				HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(404, response.getStatusCodeValue());

		// extract api response
		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(404, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertEquals("Comment not found", apiResponse.getMessage());
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
	void updateCategoryExceptionTest() {

		CommentCreateRequestDTO commentCreateRequestDTO = CommentCreateRequestDTOBuilder.withAllDummy()
				.setContent("New content EDIT").setUserId(3L).setBlogId(2L).build();

		HttpEntity<CommentCreateRequestDTO> requestEntity = new HttpEntity<>(commentCreateRequestDTO, headers);

		ResponseEntity<ApiResponse<CommentCardDTO>> response = testRestTemplate.exchange("/api/comment/2",
				HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<ApiResponse<CommentCardDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		// extract api response
		ApiResponse<CommentCardDTO> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.PUT, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertEquals("You do not have permissions to update this comment", apiResponse.getMessage());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

}
