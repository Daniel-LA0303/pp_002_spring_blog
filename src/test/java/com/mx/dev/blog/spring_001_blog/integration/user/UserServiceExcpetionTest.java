package com.mx.dev.blog.spring_001_blog.integration.user;

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

import com.mx.dev.blog.spring_001_blog.builders.user.LoginDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserCreateRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserInfoDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserUpdateInfoRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserUpdateInfoResponseDTOBuilder;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.LoginDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserAuthSuccessDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "/import.sql")
@ActiveProfiles("test")
public class UserServiceExcpetionTest {

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

	UserInfoDTO userInfoDTOBuilder;

	UserCreateRequestDTO userCreateRequestDTOBuilder;

	UserUpdateInfoResponseDTO userUpdateInfoResponseDTOBuilder;

	LoginDTO loginDTOBuilder;

	@Test
	@Order(6)
	void createUserDuplicatedExceptionTest() {

		userCreateRequestDTOBuilder = UserCreateRequestDTOBuilder.withAllDummy().setUsername("luis").build();

		HttpEntity<UserCreateRequestDTO> requestEntity = new HttpEntity<>(userCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<UserEntity>> response = testRestTemplate.exchange("/api/user", HttpMethod.POST,
				requestEntity, new ParameterizedTypeReference<ApiResponse<UserEntity>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserEntity> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		// assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(4)
	void createUserEmailDuplicatedExceptionTest() {

		userCreateRequestDTOBuilder = UserCreateRequestDTOBuilder.withAllDummy().setEmail("luis@example.com").build();

		HttpEntity<UserCreateRequestDTO> requestEntity = new HttpEntity<>(userCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<UserEntity>> response = testRestTemplate.exchange("/api/user", HttpMethod.POST,
				requestEntity, new ParameterizedTypeReference<ApiResponse<UserEntity>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserEntity> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		// assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(9)
	void createUserInvalidDataExceptionTest() {

		userCreateRequestDTOBuilder = UserCreateRequestDTOBuilder.withAllDummy().setEmail("").setUsername("")
				.setPassword("").build();

		HttpEntity<UserCreateRequestDTO> requestEntity = new HttpEntity<>(userCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<UserEntity>> response = testRestTemplate.exchange("/api/user", HttpMethod.POST,
				requestEntity, new ParameterizedTypeReference<ApiResponse<UserEntity>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserEntity> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(5)
	void getOneUpdateUserInfoExceptionTest() {

		userUpdateInfoResponseDTOBuilder = UserUpdateInfoResponseDTOBuilder.withAllDummy().build();

		ResponseEntity<ApiResponse<UserUpdateInfoResponseDTO>> response = testRestTemplate.exchange(
				"/api/user/get-user-info-to-update/100", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<UserUpdateInfoResponseDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(404, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserUpdateInfoResponseDTO> apiResponse = response.getBody();
		assertEquals(404, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(3)
	void getOneUserWithInfoExceptionTest() {

		userInfoDTOBuilder = UserInfoDTOBuilder.withAllDummy().build();

		ResponseEntity<ApiResponse<UserInfoDTO>> response = testRestTemplate.exchange("/api/user/get-user-info/100",
				HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<UserInfoDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(404, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserInfoDTO> apiResponse = response.getBody();
		assertEquals(404, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(7)
	void loginUserAuthExceptionTest() {

		loginDTOBuilder = LoginDTOBuilder.withAllDummy().setPassword("falsepassword").build();

		HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(loginDTOBuilder, headers);

		ResponseEntity<ApiResponse<UserAuthSuccessDTO>> response = testRestTemplate.exchange("/api/auth/login",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<UserAuthSuccessDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertEquals(401, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserAuthSuccessDTO> apiResponse = response.getBody();
		assertEquals(401, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		UserAuthSuccessDTO userAuthSuccessDTO = apiResponse.getData();
		assertNotNull(userAuthSuccessDTO);

	}

	@Test
	@Order(10)
	void loginUserAuthSuccessTest() {

		loginDTOBuilder = LoginDTOBuilder.withAllDummy().setEmail("").setPassword("").build();

		HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(loginDTOBuilder, headers);

		ResponseEntity<ApiResponse<UserAuthSuccessDTO>> response = testRestTemplate.exchange("/api/auth/login",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<UserAuthSuccessDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserAuthSuccessDTO> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(8)
	void loginUserNotFoundAuthExceptionTest() {

		loginDTOBuilder = LoginDTOBuilder.withAllDummy().setEmail("emailNotFound@email.com").build();

		HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(loginDTOBuilder, headers);

		ResponseEntity<ApiResponse<UserAuthSuccessDTO>> response = testRestTemplate.exchange("/api/auth/login",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<UserAuthSuccessDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertEquals(401, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserAuthSuccessDTO> apiResponse = response.getBody();
		assertEquals(401, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		// extract data
		UserAuthSuccessDTO userAuthSuccessDTO = apiResponse.getData();
		assertNotNull(userAuthSuccessDTO);

	}

	@BeforeEach
	void setUp() {

		headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

	}

	@Test
	@Order(11)
	void updateUserNotFoundExceptionTest() {

		UserUpdateInfoRequestDTO userUpdateInfoRequestDTO = UserUpdateInfoRequestDTOBuilder.withAllDummy()
				.setName("Name EDIT").build();

		HttpEntity<UserUpdateInfoRequestDTO> requestEntity = new HttpEntity<>(userUpdateInfoRequestDTO, headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/user/11", HttpMethod.PUT,
				requestEntity, new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(404, response.getStatusCodeValue());

		// extract api response
		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(404, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.PUT, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(1)
	void userFollowedUserException() {

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/user/1/follow?followerId=2",
				HttpMethod.POST, new HttpEntity<>(null, headers),
				new ParameterizedTypeReference<ApiResponse<String>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(400, response.getStatusCodeValue());

		// extract api response
		ApiResponse<String> apiResponse = response.getBody();
		assertEquals(400, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(true, apiResponse.getError());
		assertNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(2)
	void userUnfollowedUserException() {

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/user/10/unfollow?followerId=1",
				HttpMethod.DELETE, new HttpEntity<>(null, headers),
				new ParameterizedTypeReference<ApiResponse<String>>() {
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

}
