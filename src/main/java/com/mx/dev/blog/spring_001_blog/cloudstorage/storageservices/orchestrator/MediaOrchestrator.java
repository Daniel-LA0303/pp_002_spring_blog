package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.orchestrator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.service.CloudinaryService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseCloudinaryDTO;
import com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.service.S3Service;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.MediaService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.OwnerValidationService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.StorageServices;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.TypeStorage;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.mappers.CloudStorageMappers;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class MediaOrchestrator {

	private final OwnerValidationService ownerValidationService;

	private final CloudinaryService cloudinaryService;

	private final StorageServices storageServices;

	private final MediaService mediaService;

	private final S3Service s3Service;

	public MediaOrchestrator(OwnerValidationService ownerValidationService, CloudinaryService cloudinaryService,
			StorageServices storageServices, MediaService mediaService, S3Service s3Service) {
		this.ownerValidationService = ownerValidationService;
		this.cloudinaryService = cloudinaryService;
		this.storageServices = storageServices;
		this.mediaService = mediaService;
		this.s3Service = s3Service;
	}

	public ImageResponseCloudinaryDTO orchestadorUpdateImageCloudinary(String ownerType, Long ownerId,
			String categoryStorage, MultipartFile file) throws ServiceException {

		// 1. validate owner
		ownerValidationService.storageValidationOwnerTypeAndOwnerId(ownerType, ownerId);

		// 2. delete image from cloudinary
		storageServices.deleteImageCloudinary(ownerType, ownerId);

		// 3. validate extension and get extension
		TypeStorage type = storageServices.validateExtension(file);

		// 4. get folder to upload image
		String folder = storageServices.validateAndGetFolder(ownerType, categoryStorage);

		// 5. upload image
		ImageResponseCloudinaryDTO uploadResponse = cloudinaryService.upload(file, folder);

		// 6. save media in db
		mediaService.saveMedia(CloudStorageMappers.fromObjectsToMediaEntity(ownerType, ownerId, uploadResponse,
				categoryStorage.toUpperCase(), type.name()));

		return uploadResponse;
	}

	public ImageResponseCloudinaryDTO orchestadorUpdateImageS3(String ownerType, Long ownerId, String categoryStorage,
			MultipartFile file) throws ServiceException {

		// 1. validate owner
		ownerValidationService.storageValidationOwnerTypeAndOwnerId(ownerType, ownerId);

		// 2. delete image from cloudinary
		storageServices.deleteImageS3(ownerType, ownerId);

		// 3. validate extension and get extension
		TypeStorage type = storageServices.validateExtension(file);

		// 4. get folder to upload image
		String folder = storageServices.validateAndGetFolder(ownerType, categoryStorage);

		// 5. upload image
		ImageResponseCloudinaryDTO uploadResponse = s3Service.uploadFile(folder, file);

		// 6. save media in db
		mediaService.saveMedia(CloudStorageMappers.fromObjectsToMediaEntityS3(ownerType, ownerId, uploadResponse,
				categoryStorage.toUpperCase(), type.name()));

		return uploadResponse;
	}

	public ImageResponseCloudinaryDTO orchestadorUploadAWSS3(String ownerType, Long ownerId, String categoryStorage,
			MultipartFile file) throws ServiceException {

		// 1. validate owner
		ownerValidationService.storageValidationOwnerTypeAndOwnerId(ownerType, ownerId);

		// 2. validate extension and get extension
		TypeStorage type = storageServices.validateExtension(file);

		// 3. get folder to upload image
		String folder = storageServices.validateAndGetFolder(ownerType, categoryStorage);

		// 4. upload image
		ImageResponseCloudinaryDTO uploadResponse = s3Service.uploadFile(folder, file);

		// 5. save media in db
		mediaService.saveMedia(CloudStorageMappers.fromObjectsToMediaEntityS3(ownerType, ownerId, uploadResponse,
				categoryStorage.toUpperCase(), type.name()));

		return uploadResponse;
	}

	public ImageResponseCloudinaryDTO orchestadorUploadCloudinary(String ownerType, Long ownerId,
			String categoryStorage, MultipartFile file) throws ServiceException {

		// 1. validate owner
		ownerValidationService.storageValidationOwnerTypeAndOwnerId(ownerType, ownerId);

		// 2. validate extension and get extension
		TypeStorage type = storageServices.validateExtension(file);

		// 3. get folder to upload image
		String folder = storageServices.validateAndGetFolder(ownerType, categoryStorage);

		// 4. upload image
		ImageResponseCloudinaryDTO uploadResponse = cloudinaryService.upload(file, folder);

		// 5. save media in db
		mediaService.saveMedia(CloudStorageMappers.fromObjectsToMediaEntity(ownerType, ownerId, uploadResponse,
				categoryStorage.toUpperCase(), type.name()));

		return uploadResponse;
	}

}
