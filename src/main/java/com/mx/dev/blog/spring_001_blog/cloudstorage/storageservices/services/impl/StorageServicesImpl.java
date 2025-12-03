package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.service.CloudinaryService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.service.S3Service;
import com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.utils.dto.ImageResponseS3DTO;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.entities.MediaEntity;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.repositories.MediaRepository;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.StorageServices;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.CategoryStorage;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.OwnerTypeStorage;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.TypeStorage;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class StorageServicesImpl implements StorageServices {

	private final CloudinaryService cloudinaryService;

	private final MediaRepository mediaRepository;

	private final S3Service s3Service;

	public StorageServicesImpl(CloudinaryService cloudinaryService, MediaRepository mediaRepository,
			S3Service s3Service) {
		this.cloudinaryService = cloudinaryService;
		this.mediaRepository = mediaRepository;
		this.s3Service = s3Service;
	}

	// @Async
	@Override
	public void deleteImageCloudinary(String ownerType, Long ownerId) throws ServiceException {

		// 1. get media entity or throw
		MediaEntity mediaEntity = mediaRepository.findByOwnerTypeAndOwnerId(ownerType, ownerId)
				.orElseThrow(() -> new ServiceException("Not found media" + ownerType + " con ID " + ownerId, 404,
						"/delete-image", MethodEnum.DELETE));

		// 2. delete image from cloudinary
		cloudinaryService.delete(mediaEntity.getMetadata().get("public_id").toString());

		// 3. delete media from db
		mediaRepository.delete(mediaEntity);

	}

	// @Async
	@Override
	public void deleteImageS3(String ownerType, Long ownerId) throws ServiceException {
		// 1. get media entity or throw
		MediaEntity mediaEntity = mediaRepository.findByOwnerTypeAndOwnerId(ownerType, ownerId)
				.orElseThrow(() -> new ServiceException("Not found media" + ownerType + " con ID " + ownerId, 404,
						"/delete-image", MethodEnum.DELETE));

		// 2. delete image from cloudinary
		s3Service.deleteObject(mediaEntity.getMetadata().get("fileKey").toString());

		// 3. delete media from db
		mediaRepository.delete(mediaEntity);

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
			// 4. extension not valid
			throw new ServiceException("Extension " + ext + " not allowed.",
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/upload/image", MethodEnum.POST);
		}
	}

}
