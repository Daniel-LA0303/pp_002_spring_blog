package com.mx.dev.blog.spring_001_blog.integration.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import org.springframework.boot.test.mock.mockito.MockBean;
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

import com.mx.dev.blog.spring_001_blog.auth.services.email.EmailService;
import com.mx.dev.blog.spring_001_blog.builders.user.LoginDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserCreateRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserFullEngagementDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserInfoDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserSimpleResponseDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserUpdateInfoRequestDTOBuilder;
import com.mx.dev.blog.spring_001_blog.builders.user.UserUpdateInfoResponseDTOBuilder;
import com.mx.dev.blog.spring_001_blog.utils.constants.regex.AuthRegex;
import com.mx.dev.blog.spring_001_blog.utils.constants.regex.UserRegex;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.LoginDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserAuthSuccessDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserFullEngagementDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.mappers.UserMappers;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = "/import.sql")
@ActiveProfiles("test")
public class UserServiceTest {

	/**
	 * test rest template
	 */
	@Autowired
	private TestRestTemplate testRestTemplate;

	@MockBean
	private EmailService emailService;

	/**
	 * port
	 */
	@LocalServerPort
	private int port;

	/**
	 * headers
	 */
	HttpHeaders headers;

	UserSimpleResponseDTO userSimpleResponseDTOBuilder;

	UserUpdateInfoResponseDTO userUpdateInfoResponseDTOBuilder;

	UserFullEngagementDTO userFullEngagementDTOBuilder;

	UserInfoDTO userInfoDTOBuilder;

	UserCreateRequestDTO userCreateRequestDTOBuilder;

	LoginDTO loginDTOBuilder;

	@Test
	@Order(9)
	void createUserAuthSuccessTest() throws Exception {

		// MOCK → evita que se mande email real
		doNothing().when(emailService).sendRegistrationEmail(any());

		userCreateRequestDTOBuilder = UserCreateRequestDTOBuilder.withAllDummy().setEmail("email1000@email.com")
				.setPassword("1234ouybq23").setUsername("username.100").build();

		HttpEntity<UserCreateRequestDTO> requestEntity = new HttpEntity<>(userCreateRequestDTOBuilder, headers);

		ResponseEntity<ApiResponse<UserAuthSuccessDTO>> response = testRestTemplate.exchange("/api/auth/register",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<UserAuthSuccessDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(201, response.getStatusCodeValue());

		ApiResponse<UserAuthSuccessDTO> apiResponse = response.getBody();
		assertEquals(201, apiResponse.getStatus());
		assertEquals(false, apiResponse.getError());

		UserAuthSuccessDTO data = apiResponse.getData();
		// assertNotNull(data);

		// VERIFICAMOS QUE SE LLAMÓ EL EMAIL
		verify(emailService, times(1)).sendRegistrationEmail(any());
	}

	@Test
	@Order(1)
	void getAllUserSuccessTest() {

		ResponseEntity<ApiResponse> response = testRestTemplate.getForEntity("/api/user", ApiResponse.class);

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<List<UserSimpleResponseDTO>> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		List<UserSimpleResponseDTO> users = apiResponse.getData();
		assertNotNull(users);
		assertEquals(11, users.size());

	}

	@Test
	@Order(4)
	void getOneSimpleUserSuccessTest() {

		UserSimpleResponseDTO userSimpleResponseDTOBuilder = UserSimpleResponseDTOBuilder.withAllDummy().setUserId(1L)
				.setUsername("luis").setEmail("luis@example.com").setProfilePicture("pic_luis.png").build();

		ResponseEntity<ApiResponse<UserSimpleResponseDTO>> response = testRestTemplate.exchange("/api/user/1",
				HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<UserSimpleResponseDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserSimpleResponseDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		UserSimpleResponseDTO user = apiResponse.getData();
		assertNotNull(user);
		assertEquals(userSimpleResponseDTOBuilder.getUserId(), user.getUserId());
		assertEquals(userSimpleResponseDTOBuilder.getUsername(), user.getUsername());
		assertEquals(userSimpleResponseDTOBuilder.getEmail(), user.getEmail());
		// assertEquals(userSimpleResponseDTOBuilder.getProfilePicture(),
		// user.getProfilePicture());
		assertNotNull(userSimpleResponseDTOBuilder.getCreatedAt());

	}

	@Test
	@Order(5)
	void getOneUpdateUserInfoSuccessTest() {

		userUpdateInfoResponseDTOBuilder = UserUpdateInfoResponseDTOBuilder.withAllDummy().build();

		ResponseEntity<ApiResponse<UserUpdateInfoResponseDTO>> response = testRestTemplate.exchange(
				"/api/user/get-user-info-to-update/1", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<UserUpdateInfoResponseDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserUpdateInfoResponseDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		UserUpdateInfoResponseDTO user = apiResponse.getData();
		assertNotNull(user);
		assertEquals(userUpdateInfoResponseDTOBuilder.getName(), user.getName());
		assertEquals(userUpdateInfoResponseDTOBuilder.getLastName(), user.getLastName());
		assertEquals(userUpdateInfoResponseDTOBuilder.getWork(), user.getWork());
		assertEquals(userUpdateInfoResponseDTOBuilder.getEducation(), user.getEducation());
		assertEquals(userUpdateInfoResponseDTOBuilder.getPronouns(), user.getPronouns());
		assertEquals(userUpdateInfoResponseDTOBuilder.getWebsite(), user.getWebsite());
		assertEquals(userUpdateInfoResponseDTOBuilder.getAddress(), user.getAddress());
		assertEquals(userUpdateInfoResponseDTOBuilder.getCity(), user.getCity());
		assertEquals(userUpdateInfoResponseDTOBuilder.getSkills(), user.getSkills());
		assertEquals(userUpdateInfoResponseDTOBuilder.getBio(), user.getBio());

	}

	@Test
	@Order(6)
	void getOneUserWithInfoSuccessTest() {

		userInfoDTOBuilder = UserInfoDTOBuilder.withAllDummy().build();

		ResponseEntity<ApiResponse<UserInfoDTO>> response = testRestTemplate.exchange("/api/user/get-user-info/1",
				HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<UserInfoDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserInfoDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		UserInfoDTO user = apiResponse.getData();
		assertNotNull(user);
		assertEquals(userInfoDTOBuilder.getUserId(), user.getUserId());
		assertEquals(userInfoDTOBuilder.getUsername(), user.getUsername());
		assertEquals(userInfoDTOBuilder.getEmail(), user.getEmail());
		assertEquals(userInfoDTOBuilder.getBio(), user.getBio());
		assertEquals(userInfoDTOBuilder.getWork(), user.getWork());
		assertEquals(userInfoDTOBuilder.getEducation(), user.getEducation());
		assertEquals(userInfoDTOBuilder.getProfilePicture(), user.getProfilePicture());
		assertEquals(userInfoDTOBuilder.getBlogsNumber(), user.getBlogsNumber());
		assertNotNull(user.getLikesNumber());
		assertEquals(userInfoDTOBuilder.getFollowers(), user.getFollowers());
		assertNotNull(user.getCreatedAt());
		assertEquals(userInfoDTOBuilder.getWebSite(), user.getWebSite());
		assertEquals(userInfoDTOBuilder.getCategoryFollows(), user.getCategoryFollows());
		assertEquals(userInfoDTOBuilder.getCity(), user.getCity());
		assertEquals(userInfoDTOBuilder.getSkills(), user.getSkills());
		assertEquals(userInfoDTOBuilder.getUsersFollowers().size(), user.getUsersFollowers().size());

	}

	@Test
	@Order(7)
	void getUserFullEngagementSuccessTest() {

		userFullEngagementDTOBuilder = UserFullEngagementDTOBuilder.withAllDummy().build();

		ResponseEntity<ApiResponse<UserFullEngagementDTO>> response = testRestTemplate.exchange(
				"/api/user/get-user-engagement/1", HttpMethod.GET, null,
				new ParameterizedTypeReference<ApiResponse<UserFullEngagementDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserFullEngagementDTO> apiResponse = response.getBody();
		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.GET, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

		UserFullEngagementDTO user = apiResponse.getData();
		assertNotNull(user);
		assertEquals(userFullEngagementDTOBuilder.getBlogCount(), user.getBlogCount());
		// TODO check this
		assertNotNull(user.getLikesCount());
		assertNotNull(user.getReadBlogsCount());
		assertEquals(userFullEngagementDTOBuilder.getCommentCount(), user.getCommentCount());
		assertEquals(userFullEngagementDTOBuilder.getFollowingUserCount(), user.getFollowersUserCount());
		assertEquals(userFullEngagementDTOBuilder.getFollowersUserCount(), user.getFollowersUserCount());
		assertEquals(userFullEngagementDTOBuilder.getFollowingCategoryCount(), user.getFollowingCategoryCount());

	}

	@Test
	@Order(10)
	void loginUserAuthSuccessTest() {

		loginDTOBuilder = LoginDTOBuilder.withAllDummy().build();

		HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(loginDTOBuilder, headers);

		ResponseEntity<ApiResponse<UserAuthSuccessDTO>> response = testRestTemplate.exchange("/api/auth/login",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<ApiResponse<UserAuthSuccessDTO>>() {
				});

		// basic test
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());

		// extract api response
		ApiResponse<UserAuthSuccessDTO> apiResponse = response.getBody();

		assertEquals(200, apiResponse.getStatus());
		assertNotNull(apiResponse.getPath());
		assertEquals(MethodEnum.POST, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
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
	void updateUserSuccessTest() {

		UserUpdateInfoRequestDTO userUpdateInfoRequestDTO = UserUpdateInfoRequestDTOBuilder.withAllDummy()
				.setName("Name EDIT").build();

		HttpEntity<UserUpdateInfoRequestDTO> requestEntity = new HttpEntity<>(userUpdateInfoRequestDTO, headers);

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/user/1", HttpMethod.PUT,
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
		assertEquals(MethodEnum.PUT, apiResponse.getMethod());
		assertNotNull(apiResponse.getMessage());
		assertEquals(false, apiResponse.getError());
		assertNotNull(apiResponse.getData());
		assertNotNull(apiResponse.getTimestamp());

	}

	@Test
	@Order(2)
	void userFollowedUserSuccessfully() {

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/user/3/follow?followerId=1",
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
	@Order(3)
	void userUnfollowedUserSuccessfully() {

		ResponseEntity<ApiResponse<String>> response = testRestTemplate.exchange("/api/user/2/unfollow?followerId=1",
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
	void utilityConstructor_isPrivate_andThrows() throws Exception {
		Constructor<UserMappers> ctor = UserMappers.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(ctor.getModifiers()), "El ctor debe ser private");

		ctor.setAccessible(true); // forzamos acceso
		InvocationTargetException ex = assertThrows(InvocationTargetException.class, ctor::newInstance);

		assertTrue(ex.getTargetException() instanceof IllegalStateException);
		assertEquals("Utility class", ex.getTargetException().getMessage());
	}

	@Test
	void utilityConstructorIsPrivateAndThrowsAuthRegex() throws Exception {
		Constructor<AuthRegex> ctor = AuthRegex.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(ctor.getModifiers()), "El constructor debe ser private");

		ctor.setAccessible(true);
		InvocationTargetException ex = assertThrows(InvocationTargetException.class, ctor::newInstance);

		assertTrue(ex.getTargetException() instanceof IllegalStateException);
		assertEquals("Utility class", ex.getTargetException().getMessage());
	}

	@Test
	void utilityConstructorIsPrivateAndThrowsUserRegex() throws Exception {
		Constructor<UserRegex> ctor = UserRegex.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(ctor.getModifiers()), "El constructor debe ser private");

		ctor.setAccessible(true);
		InvocationTargetException ex = assertThrows(InvocationTargetException.class, ctor::newInstance);

		assertTrue(ex.getTargetException() instanceof IllegalStateException);
		assertEquals("Utility class", ex.getTargetException().getMessage());
	}
}
