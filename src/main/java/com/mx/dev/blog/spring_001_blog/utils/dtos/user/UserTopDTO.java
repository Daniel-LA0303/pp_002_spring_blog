package com.mx.dev.blog.spring_001_blog.utils.dtos.user;

public class UserTopDTO {

	/**
	 * user id
	 */
	private Long userId;

	/**
	 * name
	 */
	private String name;

	/**
	 * profile picture
	 */
	private String profilePicture;

	/**
	 * blogs counts
	 */
	private Long blogsCounts;

	/**
	 * 
	 */
	public UserTopDTO() {
	}

	/**
	 * @param userId
	 * @param name
	 * @param profilePicture
	 * @param blogsCounts
	 */
	public UserTopDTO(Long userId, String name, String profilePicture, Long blogsCounts) {
		this.userId = userId;
		this.name = name;
		this.profilePicture = profilePicture;
		this.blogsCounts = blogsCounts;
	}

	/**
	 * return the value of the property blogsCounts
	 *
	 * @return the blogsCounts
	 */
	public Long getBlogsCounts() {
		return blogsCounts;
	}

	/**
	 * return the value of the property name
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * return the value of the property userId
	 *
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * set the value of the property blogsCounts
	 *
	 * @param blogsCounts the blogsCounts to set
	 */
	public void setBlogsCounts(Long blogsCounts) {
		this.blogsCounts = blogsCounts;
	}

	/**
	 * set the value of the property name
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * set the value of the property userId
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
