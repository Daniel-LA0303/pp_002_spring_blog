package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.mappers;

import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseCloudinaryDTO;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.entities.MediaEntity;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums.ProviderStorage;

public class CloudStorageMappers {

	public static MediaEntity fromObjectsToMediaEntity(String ownerType, Long ownerId,
			ImageResponseCloudinaryDTO imageResponseCloudinaryDTO, String typeCategory, String ext) {

		// build media entity
		MediaEntity mediaEntity = new MediaEntity();

		mediaEntity.setOwnerType(ownerType);
		mediaEntity.setOwnerId(ownerId);
		mediaEntity.setProvider(ProviderStorage.CLOUDINARY.toString());
		mediaEntity.setUrlMedia(imageResponseCloudinaryDTO.getImageURL());
		mediaEntity.setCategory(typeCategory);
		mediaEntity.setTypeFile(ext);
		mediaEntity.setSizeFile(imageResponseCloudinaryDTO.getSizeFile());
		mediaEntity.setMetadata(imageResponseCloudinaryDTO.getMetadata());

		return mediaEntity;

	}

	public static MediaEntity fromObjectsToMediaEntityS3(String ownerType, Long ownerId,
			ImageResponseCloudinaryDTO imageResponseCloudinaryDTO, String typeCategory, String ext) {

		// build media entity
		MediaEntity mediaEntity = new MediaEntity();

		mediaEntity.setOwnerType(ownerType);
		mediaEntity.setOwnerId(ownerId);
		mediaEntity.setProvider(ProviderStorage.AWS.toString());
		mediaEntity.setUrlMedia(imageResponseCloudinaryDTO.getImageURL());
		mediaEntity.setCategory(typeCategory);
		mediaEntity.setTypeFile(ext);
		mediaEntity.setSizeFile(imageResponseCloudinaryDTO.getSizeFile());
		mediaEntity.setMetadata(imageResponseCloudinaryDTO.getMetadata());

		return mediaEntity;

	}

}
