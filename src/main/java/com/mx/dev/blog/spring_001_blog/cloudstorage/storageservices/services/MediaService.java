package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services;

import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.entities.MediaEntity;

public interface MediaService {

	MediaEntity getMediaByOwnerTypeAndOwnerId(String ownerType, Long ownerId);

	void saveMedia(MediaEntity mediaEntity);

}
