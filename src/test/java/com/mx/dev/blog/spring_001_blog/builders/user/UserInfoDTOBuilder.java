package com.mx.dev.blog.spring_001_blog.builders.user;

import java.time.LocalDateTime;
import java.util.List;

import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoDTO;

public class UserInfoDTOBuilder {

	private Long userId;

	private String username;

	private String email;

	private String bio;

	private String work;

	private String education;

	private String city;

	private String profilePicture;

	private String skills;

	private long blogsNumber;

	private long likesNumber;

	private long followers;

	private LocalDateTime createdAt;

	private String webSite;

	private long categoryFollows;

	private List<Long> usersFollowers;

	/**
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of UserInfoDTOBuilder
	 */
	public static UserInfoDTOBuilder withAllDummy() {
		return new UserInfoDTOBuilder().setUserId(1L).setUsername("luis").setEmail("luis@example.com")
				.setBio("Apasionado por la tecnologia y el cafe.").setWork("Desarrollador Backend")
				.setEducation("Ingenieria en Sistemas").setCity("CDMX").setProfilePicture("pic_luis.png")
				.setSkills("Java, Spring Boot, SQL").setBlogsNumber(3).setLikesNumber(11).setFollowers(4)
				.setUsersFollowers(List.of(9L, 6L, 4L, 2L)).setCategoryFollows(3).setCreatedAt(LocalDateTime.now())
				.setWebSite("http://luis.dev");
	}

	/**
	 * Builds a UserUpdateInfoResponseDTO instance.
	 *
	 * @return a new instance of UserUpdateInfoResponseDTO
	 */
	public UserInfoDTO build() {
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setUserId(userId); // user_id = 1
		userInfoDTO.setUsername(username);
		userInfoDTO.setEmail(email);
		userInfoDTO.setBio(bio);
		userInfoDTO.setWork(work);
		userInfoDTO.setEducation(education);
		userInfoDTO.setCity(city);
		userInfoDTO.setProfilePicture(profilePicture);
		userInfoDTO.setSkills(skills);
		userInfoDTO.setBlogsNumber(blogsNumber);
		userInfoDTO.setLikesNumber(likesNumber);
		userInfoDTO.setFollowers(followers);
		userInfoDTO.setUsersFollowers(usersFollowers);
		userInfoDTO.setCategoryFollows(categoryFollows);
		userInfoDTO.setCreatedAt(createdAt);
		userInfoDTO.setWebSite(webSite);

		return userInfoDTO;
	}

	/**
	 * set the value of the proppertie bio
	 *
	 * @param bio the bio to set
	 */
	public UserInfoDTOBuilder setBio(String bio) {
		this.bio = bio;
		return this;
	}

	/**
	 * set the value of the proppertie blogsNumber
	 *
	 * @param blogsNumber the blogsNumber to set
	 */
	public UserInfoDTOBuilder setBlogsNumber(long blogsNumber) {
		this.blogsNumber = blogsNumber;
		return this;
	}

	/**
	 * set the value of the proppertie categoryFollows
	 *
	 * @param categoryFollows the categoryFollows to set
	 */
	public UserInfoDTOBuilder setCategoryFollows(long categoryFollows) {
		this.categoryFollows = categoryFollows;
		return this;
	}

	/**
	 * set the value of the proppertie city
	 *
	 * @param city the city to set
	 */
	public UserInfoDTOBuilder setCity(String city) {
		this.city = city;
		return this;
	}

	/**
	 * set the value of the proppertie createdAt
	 *
	 * @param createdAt the createdAt to set
	 */
	public UserInfoDTOBuilder setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	/**
	 * set the value of the proppertie education
	 *
	 * @param education the education to set
	 */
	public UserInfoDTOBuilder setEducation(String education) {
		this.education = education;
		return this;
	}

	/**
	 * set the value of the proppertie email
	 *
	 * @param email the email to set
	 */
	public UserInfoDTOBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * set the value of the proppertie followers
	 *
	 * @param followers the followers to set
	 */
	public UserInfoDTOBuilder setFollowers(long followers) {
		this.followers = followers;
		return this;
	}

	/**
	 * set the value of the proppertie likesNumber
	 *
	 * @param likesNumber the likesNumber to set
	 */
	public UserInfoDTOBuilder setLikesNumber(long likesNumber) {
		this.likesNumber = likesNumber;
		return this;
	}

	/**
	 * set the value of the proppertie profilePicture
	 *
	 * @param profilePicture the profilePicture to set
	 */
	public UserInfoDTOBuilder setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
		return this;
	}

	/**
	 * set the value of the proppertie skills
	 *
	 * @param skills the skills to set
	 */
	public UserInfoDTOBuilder setSkills(String skills) {
		this.skills = skills;
		return this;
	}

	/**
	 * set the value of the proppertie userId
	 *
	 * @param userId the userId to set
	 */
	public UserInfoDTOBuilder setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * set the value of the proppertie username
	 *
	 * @param username the username to set
	 */
	public UserInfoDTOBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	/**
	 * set the value of the proppertie usersFollowers
	 *
	 * @param usersFollowers the usersFollowers to set
	 */
	public UserInfoDTOBuilder setUsersFollowers(List<Long> usersFollowers) {
		this.usersFollowers = usersFollowers;
		return this;
	}

	/**
	 * set the value of the proppertie webSite
	 *
	 * @param webSite the webSite to set
	 */
	public UserInfoDTOBuilder setWebSite(String webSite) {
		this.webSite = webSite;
		return this;
	}

	/**
	 * set the value of the proppertie work
	 *
	 * @param work the work to set
	 */
	public UserInfoDTOBuilder setWork(String work) {
		this.work = work;
		return this;
	}

}
