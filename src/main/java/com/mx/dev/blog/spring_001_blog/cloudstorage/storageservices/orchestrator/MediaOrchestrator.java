package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.orchestrator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.service.CloudinaryService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseCloudinaryDTO;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.MediaService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.StorageValidationService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.impl.StorageServicesImpl;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.TypeStorage;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.mappers.CloudStorageMappers;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class MediaOrchestrator {

	private final StorageValidationService validationService;

	private final CloudinaryService cloudinaryService;

	private final StorageServicesImpl storageServicesImpl;

	private final MediaService mediaService;

	public MediaOrchestrator(StorageValidationService validationService, CloudinaryService cloudinaryService,
			StorageServicesImpl storageServicesImpl, MediaService mediaService) {
		this.validationService = validationService;
		this.cloudinaryService = cloudinaryService;
		this.storageServicesImpl = storageServicesImpl;
		this.mediaService = mediaService;
	}

	public ImageResponseCloudinaryDTO orchestadorUploadCloudinary(String ownerType, Long ownerId,
			String categoryStorage, MultipartFile file) throws ServiceException {

		// 1. validate owner
		validationService.storageValidationUploadCloudinary(ownerType, ownerId);

		// 2. validate extension
		TypeStorage type = storageServicesImpl.validateExtension(file);

		// 3. get folder to upload image
		String folder = storageServicesImpl.validateAndGetFolder(ownerType, categoryStorage);

		// 4. upload image
		ImageResponseCloudinaryDTO uploadResponse = cloudinaryService.upload(file, folder);

		// 5. save media in db
		mediaService.saveMedia(CloudStorageMappers.fromObjectsToMediaEntity(ownerType, ownerId, uploadResponse,
				categoryStorage.toUpperCase(), type.name()));

		return uploadResponse;
	}

}
