package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.entities.MediaEntity;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.repositories.MediaRepository;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.services.MediaService;

@Service
public class MediaServiceImpl implements MediaService {

	private final MediaRepository mediaRepository;

	public MediaServiceImpl(MediaRepository mediaRepository) {
		this.mediaRepository = mediaRepository;
	}

	@Override
	public MediaEntity getMediaByOwnerTypeAndOwnerId(String ownerType, Long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Async
	@Override
	public void saveMedia(MediaEntity mediaEntity) {
		mediaRepository.save(mediaEntity);
	}

}
