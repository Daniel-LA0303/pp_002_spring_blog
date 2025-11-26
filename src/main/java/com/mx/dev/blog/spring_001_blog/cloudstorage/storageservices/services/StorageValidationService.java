package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services;

import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface StorageValidationService {

	void storageValidationUploadCloudinary(String ownerType, Long ownerId) throws ServiceException;

}
