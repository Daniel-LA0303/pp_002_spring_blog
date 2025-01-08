package com.mx.dev.blog.spring_001_blog.entities.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info_tbl")
public class UserInfoEntity {

	/**
	 * user info id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userInfoId;

	/**
	 * profile picture
	 */
	@Column(name = "profile_picture")
	private String profilePicture;

	/**
	 * bio
	 */
	@Column(name = "bio")
	private String bio;

	/**
	 * last login
	 */
	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	/**
	 * is active
	 */
	@Column(name = "is_active")
	private Boolean isActive;

	/**
	 * phone
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * direction
	 */
	@Column(name = "direction")
	private String direction;

	/**
	 * user id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * name
	 */
	@Column(name = "name")
	private String name;

	/**
	 * last name
	 */
	@Column(name = "lastname")
	private String lastName;

	/**
	 * work
	 */
	@Column(name = "work")
	private String work;

	/**
	 * education
	 */
	@Column(name = "education")
	private String education;

	/**
	 * pronouns
	 */
	@Column(name = "pronouns")
	private String pronouns;

	/**
	 * website
	 */
	@Column(name = "website")
	private String website;

	/**
	 * city
	 */
	@Column(name = "city")
	private String city;

	/**
	 * skills
	 */
	@Column(name = "skills")
	private String skills;

	/**
	 * 
	 */
	public UserInfoEntity() {
	}

	/**
	 * @param userInfoId
	 * @param profilePicture
	 * @param bio
	 * @param lastLogin
	 * @param isActive
	 * @param phone
	 * @param direction
	 * @param userId
	 * @param name
	 * @param lastName
	 * @param work
	 * @param education
	 * @param pronouns
	 * @param website
	 * @param city
	 * @param skills
	 */
	public UserInfoEntity(Long userInfoId, String profilePicture, String bio, LocalDateTime lastLogin, Boolean isActive,
			String phone, String direction, Long userId, String name, String lastName, String work, String education,
			String pronouns, String website, String city, String skills) {
		this.userInfoId = userInfoId;
		this.profilePicture = profilePicture;
		this.bio = bio;
		this.lastLogin = lastLogin;
		this.isActive = isActive;
		this.phone = phone;
		this.direction = direction;
		this.userId = userId;
		this.name = name;
		this.lastName = lastName;
		this.work = work;
		this.education = education;
		this.pronouns = pronouns;
		this.website = website;
		this.city = city;
		this.skills = skills;
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
	 * return the value of the property city
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * return the value of the property direction
	 *
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
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
	 * return the value of the property isActive
	 *
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * return the value of the property lastLogin
	 *
	 * @return the lastLogin
	 */
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	/**
	 * return the value of the property lastName
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
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
	 * return the value of the property phone
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
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
	 * return the value of the property pronouns
	 *
	 * @return the pronouns
	 */
	public String getPronouns() {
		return pronouns;
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
	 * return the value of the property userInfoId
	 *
	 * @return the userInfoId
	 */
	public Long getUserInfoId() {
		return userInfoId;
	}

	/**
	 * return the value of the property website
	 *
	 * @return the website
	 */
	public String getWebsite() {
		return website;
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
	 * set the value of the property city
	 *
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * set the value of the property direction
	 *
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
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
	 * set the value of the property isActive
	 *
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * set the value of the property lastLogin
	 *
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * set the value of the property lastName
	 *
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * set the value of the property phone
	 *
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * set the value of the property pronouns
	 *
	 * @param pronouns the pronouns to set
	 */
	public void setPronouns(String pronouns) {
		this.pronouns = pronouns;
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
	 * set the value of the property userInfoId
	 *
	 * @param userInfoId the userInfoId to set
	 */
	public void setUserInfoId(Long userInfoId) {
		this.userInfoId = userInfoId;
	}

	/**
	 * set the value of the property website
	 *
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
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
