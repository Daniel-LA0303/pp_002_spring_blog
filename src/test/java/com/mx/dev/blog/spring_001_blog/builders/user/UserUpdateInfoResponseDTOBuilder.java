package com.mx.dev.blog.spring_001_blog.builders.user;

import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserUpdateInfoResponseDTO;

public class UserUpdateInfoResponseDTOBuilder {

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
	 * Creates a builder with all dummy data for testing purposes.
	 *
	 * @return a pre-filled instance of UserUpdateInfoResponseDTOBuilder
	 */
	public static UserUpdateInfoResponseDTOBuilder withAllDummy() {
		return new UserUpdateInfoResponseDTOBuilder().setName("Luis").setLastName("Martinez")
				.setWork("Desarrollador Backend").setEducation("Ingenieria en Sistemas").setPronouns("el")
				.setWebsite("http://luis.dev").setAddress("Calle 1 #123").setCity("CDMX")
				.setSkills("Java, Spring Boot, SQL").setBio("Apasionado por la tecnologia y el cafe.");
	}

	/**
	 * Builds a UserUpdateInfoResponseDTO instance.
	 *
	 * @return a new instance of UserUpdateInfoResponseDTO
	 */
	public UserUpdateInfoResponseDTO build() {
		UserUpdateInfoResponseDTO userUpdateInfoResponseDTO = new UserUpdateInfoResponseDTO();
		userUpdateInfoResponseDTO.setName(name);
		userUpdateInfoResponseDTO.setLastName(lastName);
		userUpdateInfoResponseDTO.setWork(work);
		userUpdateInfoResponseDTO.setCity(city);
		userUpdateInfoResponseDTO.setEducation(education);
		userUpdateInfoResponseDTO.setPronouns(pronouns);
		userUpdateInfoResponseDTO.setWebsite(website);
		userUpdateInfoResponseDTO.setAddress(address);
		userUpdateInfoResponseDTO.setSkills(skills);
		userUpdateInfoResponseDTO.setBio(bio);

		return userUpdateInfoResponseDTO;
	}

	/**
	 * set the value of the proppertie address
	 *
	 * @param address the address to set
	 */
	public UserUpdateInfoResponseDTOBuilder setAddress(String address) {
		this.address = address;
		return this;
	}

	/**
	 * set the value of the proppertie bio
	 *
	 * @param bio the bio to set
	 */
	public UserUpdateInfoResponseDTOBuilder setBio(String bio) {
		this.bio = bio;
		return this;
	}

	/**
	 * set the value of the proppertie city
	 *
	 * @param city the city to set
	 */
	public UserUpdateInfoResponseDTOBuilder setCity(String city) {
		this.city = city;
		return this;
	}

	/**
	 * set the value of the proppertie education
	 *
	 * @param education the education to set
	 */
	public UserUpdateInfoResponseDTOBuilder setEducation(String education) {
		this.education = education;
		return this;
	}

	/**
	 * set the value of the proppertie lastName
	 *
	 * @param lastName the lastName to set
	 */
	public UserUpdateInfoResponseDTOBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * set the value of the proppertie name
	 *
	 * @param name the name to set
	 */
	public UserUpdateInfoResponseDTOBuilder setName(String name) {
		this.name = name;
		return this;

	}

	/**
	 * set the value of the proppertie pronouns
	 *
	 * @param pronouns the pronouns to set
	 */
	public UserUpdateInfoResponseDTOBuilder setPronouns(String pronouns) {
		this.pronouns = pronouns;
		return this;
	}

	/**
	 * set the value of the proppertie skills
	 *
	 * @param skills the skills to set
	 */
	public UserUpdateInfoResponseDTOBuilder setSkills(String skills) {
		this.skills = skills;
		return this;
	}

	/**
	 * set the value of the proppertie website
	 *
	 * @param website the website to set
	 */
	public UserUpdateInfoResponseDTOBuilder setWebsite(String website) {
		this.website = website;
		return this;
	}

	/**
	 * set the value of the proppertie work
	 *
	 * @param work the work to set
	 */
	public UserUpdateInfoResponseDTOBuilder setWork(String work) {
		this.work = work;
		return this;
	}

}
