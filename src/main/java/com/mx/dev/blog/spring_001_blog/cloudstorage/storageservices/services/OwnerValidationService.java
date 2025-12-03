package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services;

import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface OwnerValidationService {

	void storageValidationOwnerTypeAndOwnerId(String ownerType, Long ownerId) throws ServiceException;

}
