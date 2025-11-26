package com.mx.dev.blog.spring_001_blog.integration.replyService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

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
import com.mx.dev.blog.spring_001_blog.dto.PageDTO;
import com.mx.dev.blog.spring_001_blog.utils.constants.regex.ReplyRegex;
import com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "/import.sql")
@ActiveProfiles("test")
public class ReplyServiceTest {

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
	void createCommentSuccessTest() {

		HttpEntity<ReplyCreateRequestDTO> requestEntity = new HttpEntity<>(replyCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<ReplyCardDTO>> response = testRestTemplate.exchange("/api/reply", HttpMethod.POST,
				requestEntity, new ParameterizedTypeReference<ApiResponse<ReplyCardDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<ReplyCardDTO> apiResponse = response.getBody();
		assertEquals(201, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		ReplyCardDTO replyCardDTO = apiResponse.getData();
		assertNotNull(replyCardDTO);

		// check data
		assertNotNull(replyCardDTO.getCommentId());
		assertEquals(replyCreateRequestDTOBuilder.getUserId(), replyCardDTO.getUserId());
		assertEquals(replyCreateRequestDTOBuilder.getContent(), replyCardDTO.getContent());
		assertEquals(replyCreateRequestDTOBuilder.getBlogId(), replyCardDTO.getBlogId());
		assertEquals(replyCreateRequestDTOBuilder.getCommentId(), replyCardDTO.getCommentId());

	}

	@Test
	@Order(1)
	void getRepliesByCommentSuccessTest() {

		ResponseEntity<ApiResponse<PageDTO<ReplyCardDTO>>> response = testRestTemplate.exchange(
				"/api/reply/get-replies-by-comment/2?page=0&size=10", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<PageDTO<ReplyCardDTO>>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<PageDTO<ReplyCardDTO>> apiResponse = response.getBody();
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

		replyCreateRequestDTOBuilder = ReplyCreateRequestDTOBuilder.withAllDummy().build();

		headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	@Test
	@Order(3)
	void updateCommentSuccessTest() {

		ReplyCreateRequestDTO replyCreateRequestDTO = ReplyCreateRequestDTOBuilder.withAllDummy()
				.setContent("New reply EDIT").setUserId(2L).build();

		HttpEntity<ReplyCreateRequestDTO> requestEntity = new HttpEntity<>(replyCreateRequestDTO, headers);

		ResponseEntity<ApiResponse<ReplyCardDTO>> response = testRestTemplate.exchange("/api/reply/2", HttpMethod.PUT,
				requestEntity, new ParameterizedTypeReference<ApiResponse<ReplyCardDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<ReplyCardDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.PUT, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		ReplyCardDTO replyCardDTO = apiResponse.getData();
		assertNotNull(replyCardDTO);

		// check data
		assertNotNull(replyCardDTO.getCommentId());
		assertEquals(replyCreateRequestDTO.getUserId(), replyCardDTO.getUserId());
		assertEquals(replyCreateRequestDTO.getContent(), replyCardDTO.getContent());
		assertEquals(replyCreateRequestDTO.getBlogId(), replyCardDTO.getBlogId());
		assertEquals(replyCreateRequestDTO.getCommentId(), replyCardDTO.getCommentId());

	}

	@Test
	void utilityConstructorIsPrivateAndThrowsReplyRegex() throws Exception {
		Constructor<ReplyRegex> ctor = ReplyRegex.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(ctor.getModifiers()), "El constructor debe ser private");

		ctor.setAccessible(true);
		InvocationTargetException ex = assertThrows(InvocationTargetException.class, ctor::newInstance);

		assertTrue(ex.getTargetException() instanceof IllegalStateException);
		assertEquals("Utility class", ex.getTargetException().getMessage());
	}

}
