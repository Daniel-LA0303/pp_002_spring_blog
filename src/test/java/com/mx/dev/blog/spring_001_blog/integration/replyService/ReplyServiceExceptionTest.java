package com.mx.dev.blog.spring_001_blog.integration.replyService;

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

import com.mx.dev.blog.spring_001_blog.builders.reply.ReplyCreateRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "/import.sql")
@ActiveProfiles("test")
public class ReplyServiceExceptionTest {

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

	ReplyCreateRequestDTO replyCreateRequestDTOBuilder;

	@Test
	@Order(2)
	void createCommentInvalidDataExceptionTest() {

		replyCreateRequestDTOBuilder = ReplyCreateRequestDTOBuilder.withAllDummy().setBlogId(2L).setCommentId(1L)
				.setContent("").setUserId(1L).build();

		HttpEntity<ReplyCreateRequestDTO> requestEntity = new HttpEntity<>(replyCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<ReplyCardDTO>> response = testRestTemplate.exchange("/api/reply", HttpMethod.POST,
				requestEntity, new ParameterizedTypeReference<ApiResponse<ReplyCardDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		// extract api response
		ApiResponse<ReplyCardDTO> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());
	}

	@BeforeEach
	void setUp() {

		headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	@Test
	@Order(1)
	void updateCommentExceptionTest() {

		ReplyCreateRequestDTO replyCreateRequestDTO = ReplyCreateRequestDTOBuilder.withAllDummy()
				.setContent("New reply EDIT").setUserId(3L).build();

		HttpEntity<ReplyCreateRequestDTO> requestEntity = new HttpEntity<>(replyCreateRequestDTO, headers);

		ResponseEntity<ApiResponse<ReplyCardDTO>> response = testRestTemplate.exchange("/api/reply/2", HttpMethod.PUT,
				requestEntity, new ParameterizedTypeReference<ApiResponse<ReplyCardDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		// extract api response
		ApiResponse<ReplyCardDTO> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.PUT, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(2)
	void updateNotFoundCommentExceptionTest() {

		ReplyCreateRequestDTO replyCreateRequestDTO = ReplyCreateRequestDTOBuilder.withAllDummy()
				.setContent("New reply EDIT").setUserId(3L).build();

		HttpEntity<ReplyCreateRequestDTO> requestEntity = new HttpEntity<>(replyCreateRequestDTO, headers);

		ResponseEntity<ApiResponse<ReplyCardDTO>> response = testRestTemplate.exchange("/api/reply/50", HttpMethod.PUT,
				requestEntity, new ParameterizedTypeReference<ApiResponse<ReplyCardDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(404, response.getStatusCodeValue());

		// extract api response
		ApiResponse<ReplyCardDTO> apiResponse = response.getBody();
		assertEquals(404, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

}
