package com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.service;

import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseCloudinaryDTO;

public interface S3Service {

	void deleteObject(String fileKey);

	ImageResponseCloudinaryDTO uploadFile(String folder, MultipartFile file);

}
