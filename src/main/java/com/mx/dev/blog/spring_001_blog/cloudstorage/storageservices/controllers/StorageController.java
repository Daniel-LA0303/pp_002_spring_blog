package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseCloudinaryDTO;
import com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.orchestrator.MediaOrchestrator;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@RestController
@RequestMapping("/storage/v1")
public class StorageController {

	private final MediaOrchestrator orchestrator;

	public StorageController(MediaOrchestrator orchestrator) {
		this.orchestrator = orchestrator;
	}

	@PutMapping(value = "/update-upload-image-cloudinary")
	public ResponseEntity<?> updateUploadImageCloudinary(@RequestParam("image") MultipartFile file,
			@RequestParam("ownerType") String ownerType, @RequestParam("ownerId") Long ownerId,
			@RequestParam("categoryStorage") String categoryStorage) throws ServiceException {

		// orchestator
		ImageResponseCloudinaryDTO result = orchestrator.orchestadorUpdateImageCloudinary(ownerType, ownerId,
				categoryStorage, file);

		ApiResponse<ImageResponseCloudinaryDTO> apiResponse = new ApiResponse<>(
				ResponseStatus.CREATED.getHttpStatusCode(),
				"/storage/v1/uploads/upload-image-cloudinary?ownerType=" + ownerType + "&ownerId=" + ownerId
						+ "&categoryStorage=" + categoryStorage,
				MethodEnum.POST, "Image uploaded successfully", result, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

	@PostMapping(value = "/upload-image-cloudinary")
	public ResponseEntity<?> uploadImageCloudinary(@RequestParam("image") MultipartFile file,
			@RequestParam("ownerType") String ownerType, @RequestParam("ownerId") Long ownerId,
			@RequestParam("categoryStorage") String categoryStorage) throws ServiceException {

		// orchestator
		ImageResponseCloudinaryDTO result = orchestrator.orchestadorUploadCloudinary(ownerType, ownerId,
				categoryStorage, file);

		ApiResponse<ImageResponseCloudinaryDTO> apiResponse = new ApiResponse<>(
				ResponseStatus.CREATED.getHttpStatusCode(),
				"/storage/v1/uploads/upload-image-cloudinary?ownerType=" + ownerType + "&ownerId=" + ownerId
						+ "&categoryStorage=" + categoryStorage,
				MethodEnum.POST, "Image uploaded successfully", result, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

}
