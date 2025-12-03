package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.validations;

import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface OwnerValidator {

	String getOwnerType();

	void validate(Long ownerId) throws ServiceException;
}
