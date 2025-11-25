package com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto;

public class ImageResponseDTO {

	private String imageURL;

	private String publicId;

	public ImageResponseDTO() {
	}

	/**
	 * @param imageURL
	 * @param publicId
	 */
	public ImageResponseDTO(String imageURL, String publicId) {
		this.imageURL = imageURL;
		this.publicId = publicId;
	}

	/**
	 * return value of the property imageURL
	 *
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * return value of the property publicId
	 *
	 * @return the publicId
	 */
	public String getPublicId() {
		return publicId;
	}

	/**
	 * set value of the property imageURL
	 *
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * set value of the property publicId
	 *
	 * @param publicId the publicId to set
	 */
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

}
