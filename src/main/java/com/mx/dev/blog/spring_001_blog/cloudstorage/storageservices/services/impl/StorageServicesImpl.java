package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.utils.dto.ImageResponseS3DTO;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.StorageServices;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.CategoryStorage;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.OwnerTypeStorage;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.TypeStorage;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class StorageServicesImpl implements StorageServices {

	public StorageServicesImpl() {

	}

	@Override
	public void deleteImageCloudinary(MultipartFile file) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteImageS3(MultipartFile file) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public ImageResponseS3DTO uploadImageS3(MultipartFile file) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validateAndGetFolder(String ownerType, String categoryStorage) throws ServiceException {

		try {
			// 1. validate owner type
			OwnerTypeStorage.valueOf(ownerType.toUpperCase());

			// 2. validate category
			CategoryStorage.valueOf(categoryStorage.toUpperCase());

			// 3. return folder
			return OwnerTypeStorage.valueOf(ownerType.toUpperCase()).getFolder();
		} catch (IllegalArgumentException e) {
			throw new ServiceException("OwnerType o Category desconocidos", 400, "/get-folder", MethodEnum.GET);
		}

	}

	@Override
	public TypeStorage validateExtension(MultipartFile file) throws ServiceException {

		// 1. check valid file
		if (file.getOriginalFilename() == null) {
			throw new ServiceException("Invalid file name.", ResponseStatus.BAD_REQUEST.getHttpStatusCode(),
					"/upload/image", MethodEnum.POST);
		}

		// 2. extract extension
		String[] parts = file.getOriginalFilename().split("\\.");
		String ext = parts[parts.length - 1].toUpperCase();

		try {
			// 3. check if extension is valid and return
			return TypeStorage.valueOf(ext);
		} catch (IllegalArgumentException ex) {
			// 5. extension not valid
			throw new ServiceException("Extension " + ext + " not allowed.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/upload/image", MethodEnum.POST);
		}
	}

}
