package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.entities.MediaEntity;

@Repository
public interface MediaRepository extends JpaRepository<MediaEntity, Long> {

	// get by owner id
	@Query("SELECT m FROM MediaEntity m WHERE m.ownerId = :ownerId")
	Optional<MediaEntity> findByOwnerId(@Param("ownerId") Long ownerId);

	// boolean getMediaByOwnerTypeAndOwnerId();
	@Query("SELECT m FROM MediaEntity m WHERE m.ownerType = :ownerType AND m.ownerId = :ownerId")
	Optional<MediaEntity> findByOwnerTypeAndOwnerId(@Param("ownerType") String ownerType,
			@Param("ownerId") Long ownerId);

}
