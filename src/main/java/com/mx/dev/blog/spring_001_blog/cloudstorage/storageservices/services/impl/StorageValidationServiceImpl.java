package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.StorageValidationService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.validations.OwnerValidator;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class StorageValidationServiceImpl implements StorageValidationService {

	private final Map<String, OwnerValidator> validators;

	public StorageValidationServiceImpl(List<OwnerValidator> validatorsList) {
		this.validators = validatorsList.stream()
				.collect(Collectors.toMap(OwnerValidator::getOwnerType, Function.identity()));
	}

	@Override
	public void storageValidationUploadCloudinary(String ownerType, Long ownerId) throws ServiceException {
		OwnerValidator validator = validators.get(ownerType);
		if (validator == null) {
			throw new IllegalArgumentException("Tipo de owner desconocido: " + ownerType);
		}
		validator.validate(ownerId);
	}

}
