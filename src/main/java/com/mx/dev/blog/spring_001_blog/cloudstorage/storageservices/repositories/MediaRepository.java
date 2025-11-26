package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.entities.MediaEntity;

@Repository
public interface MediaRepository extends JpaRepository<MediaEntity, Long> {

	// boolean getMediaByOwnerTypeAndOwnerId();

}
