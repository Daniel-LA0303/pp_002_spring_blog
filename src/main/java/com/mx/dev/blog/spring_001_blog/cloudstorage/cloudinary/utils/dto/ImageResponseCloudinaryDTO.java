package com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto;

import java.util.Map;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class ImageResponseCloudinaryDTO {

	private String imageURL;

	private Double sizeFile;

	@Type(type = "jsonb")
	private Map<String, Object> metadata;

	public ImageResponseCloudinaryDTO() {
	}

	/**
	 * @param imageURL
	 * @param publicId
	 * @param sizeFile
	 * @param metadata
	 */
	public ImageResponseCloudinaryDTO(String imageURL, Double sizeFile, Map<String, Object> metadata) {
		this.imageURL = imageURL;
		this.sizeFile = sizeFile;
		this.metadata = metadata;
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
	 * return value of the property metadata
	 *
	 * @return the metadata
	 */
	public Map<String, Object> getMetadata() {
		return metadata;
	}

	/**
	 * return value of the property sizeFile
	 *
	 * @return the sizeFile
	 */
	public Double getSizeFile() {
		return sizeFile;
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
	 * set value of the property metadata
	 *
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}

	/**
	 * set value of the property sizeFile
	 *
	 * @param sizeFile the sizeFile to set
	 */
	public void setSizeFile(Double sizeFile) {
		this.sizeFile = sizeFile;
	}

}
