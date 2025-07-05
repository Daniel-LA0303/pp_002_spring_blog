package com.mx.dev.blog.spring_001_blog.utils.dtos.user;

import org.springframework.web.multipart.MultipartFile;

public class UserUpdateInfoRequestDTO {

	/**
	 * name
	 */
	private String name;

	/**
	 * lastName
	 */
	private String lastName;

	/**
	 * work
	 */
	private String work;

	/**
	 * education
	 */
	private String education;

	/**
	 * education
	 */
	private String pronouns;

	/**
	 * website
	 */
	private String website;

	/**
	 * address
	 */
	private String address;

	/**
	 * city
	 */
	private String city;

	/**
	 * skills
	 */
	private String skills;

	/**
	 * bio
	 */
	private String bio;

	/**
	 * user image
	 */
	private MultipartFile userImage;

	/**
	 * profile picture
	 */

	/**
	 * 
	 */
	public UserUpdateInfoRequestDTO() {
	}

	/**
	 * @param name
	 * @param lastName
	 * @param work
	 * @param education
	 * @param pronouns
	 * @param website
	 * @param address
	 * @param city
	 * @param skills
	 * @param bio
	 * @param userImage
	 */
	public UserUpdateInfoRequestDTO(String name, String lastName, String work, String education, String pronouns,
			String website, String address, String city, String skills, String bio, MultipartFile userImage) {
		this.name = name;
		this.lastName = lastName;
		this.work = work;
		this.education = education;
		this.pronouns = pronouns;
		this.website = website;
		this.address = address;
		this.city = city;
		this.skills = skills;
		this.bio = bio;
		this.userImage = userImage;
	}

	/**
	 * return the value of the property address
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
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
	 * return the value of the property education
	 *
	 * @return the education
	 */
	public String getEducation() {
		return education;
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
	 * return the value of the property userImage
	 *
	 * @return the userImage
	 */
	public MultipartFile getUserImage() {
		return userImage;
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
	 * set the value of the property address
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * set the value of the property education
	 *
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
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
	 * set the value of the property userImage
	 *
	 * @param userImage the userImage to set
	 */
	public void setUserImage(MultipartFile userImage) {
		this.userImage = userImage;
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
