package com.mx.dev.blog.spring_001_blog.utils.dtos.user;

import java.time.LocalDateTime;

public class UserInfoDTO {

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

	/**
	 * 
	 */
	public UserInfoDTO() {
	}

	/**
	 * @param userId
	 * @param username
	 * @param email
	 * @param bio
	 * @param work
	 * @param education
	 * @param city
	 * @param profilePicture
	 * @param skills
	 * @param blogsNumber
	 * @param likesNumber
	 * @param followers
	 * @param createdAt
	 */
	public UserInfoDTO(Long userId, String username, String email, String bio, String work, String education,
			String city, String profilePicture, String skills, long blogsNumber, long likesNumber, long followers,
			LocalDateTime createdAt, String webSite, long categoryFollows) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.bio = bio;
		this.work = work;
		this.education = education;
		this.city = city;
		this.profilePicture = profilePicture;
		this.skills = skills;
		this.blogsNumber = blogsNumber;
		this.likesNumber = likesNumber;
		this.followers = followers;
		this.createdAt = createdAt;
		this.webSite = webSite;
		this.categoryFollows = categoryFollows;
	}

	/**
	 * return the value of the property bio
	 *
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * return the value of the property blogsNumber
	 *
	 * @return the blogsNumber
	 */
	public long getBlogsNumber() {
		return blogsNumber;
	}

	/**
	 * return the value of the property categoryFollows
	 *
	 * @return the categoryFollows
	 */
	public long getCategoryFollows() {
		return categoryFollows;
	}

	/**
	 * return the value of the property city
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * return the value of the property createdAt
	 *
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * return the value of the property education
	 *
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * return the value of the property email
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * return the value of the property followers
	 *
	 * @return the followers
	 */
	public long getFollowers() {
		return followers;
	}

	/**
	 * return the value of the property likesNumber
	 *
	 * @return the likesNumber
	 */
	public long getLikesNumber() {
		return likesNumber;
	}

	/**
	 * return the value of the property profilePicture
	 *
	 * @return the profilePicture
	 */
	public String getProfilePicture() {
		return profilePicture;
	}

	/**
	 * return the value of the property skills
	 *
	 * @return the skills
	 */
	public String getSkills() {
		return skills;
	}

	/**
	 * return the value of the property userId
	 *
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * return the value of the property username
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * return the value of the property webSite
	 *
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * return the value of the property work
	 *
	 * @return the work
	 */
	public String getWork() {
		return work;
	}

	/**
	 * set the value of the property bio
	 *
	 * @param bio the bio to set
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * set the value of the property blogsNumber
	 *
	 * @param blogsNumber the blogsNumber to set
	 */
	public void setBlogsNumber(long blogsNumber) {
		this.blogsNumber = blogsNumber;
	}

	/**
	 * set the value of the property categoryFollows
	 *
	 * @param categoryFollows the categoryFollows to set
	 */
	public void setCategoryFollows(long categoryFollows) {
		this.categoryFollows = categoryFollows;
	}

	/**
	 * set the value of the property city
	 *
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * set the value of the property createdAt
	 *
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * set the value of the property education
	 *
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * set the value of the property email
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * set the value of the property followers
	 *
	 * @param followers the followers to set
	 */
	public void setFollowers(long followers) {
		this.followers = followers;
	}

	/**
	 * set the value of the property likesNumber
	 *
	 * @param likesNumber the likesNumber to set
	 */
	public void setLikesNumber(long likesNumber) {
		this.likesNumber = likesNumber;
	}

	/**
	 * set the value of the property profilePicture
	 *
	 * @param profilePicture the profilePicture to set
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * set the value of the property skills
	 *
	 * @param skills the skills to set
	 */
	public void setSkills(String skills) {
		this.skills = skills;
	}

	/**
	 * set the value of the property userId
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * set the value of the property username
	 *
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * set the value of the property webSite
	 *
	 * @param webSite the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	/**
	 * set the value of the property work
	 *
	 * @param work the work to set
	 */
	public void setWork(String work) {
		this.work = work;
	}

}
