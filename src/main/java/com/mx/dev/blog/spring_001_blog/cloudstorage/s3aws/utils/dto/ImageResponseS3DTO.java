package com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.utils.dto;

public class ImageResponseS3DTO {

	private String bucketName;

	private String fileKey;

	/**
	 * 
	 */
	public ImageResponseS3DTO() {
	}

	/**
	 * @param bucketName
	 * @param fileKey
	 */
	public ImageResponseS3DTO(String bucketName, String fileKey) {
		this.bucketName = bucketName;
		this.fileKey = fileKey;
	}

	/**
	 * return value of the property bucketName
	 *
	 * @return the bucketName
	 */
	public String getBucketName() {
		return bucketName;
	}

	/**
	 * return value of the property fileKey
	 *
	 * @return the fileKey
	 */
	public String getFileKey() {
		return fileKey;
	}

	/**
	 * set value of the property bucketName
	 *
	 * @param bucketName the bucketName to set
	 */
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	/**
	 * set value of the property fileKey
	 *
	 * @param fileKey the fileKey to set
	 */
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

}
