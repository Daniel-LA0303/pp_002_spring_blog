package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services;

import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.utils.dto.ImageResponseS3DTO;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.TypeStorage;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface StorageServices {

	void deleteImageCloudinary(String ownerType, Long ownerId) throws ServiceException;

	void deleteImageS3(String ownerType, Long ownerId) throws ServiceException;

	ImageResponseS3DTO uploadImageS3(MultipartFile file) throws ServiceException;

	String validateAndGetFolder(String ownerType, String categoryStorage) throws ServiceException;

	TypeStorage validateExtension(MultipartFile file) throws ServiceException;
}
