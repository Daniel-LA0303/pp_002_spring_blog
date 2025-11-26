package com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.service.CloudinaryService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseCloudinaryDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@RestController
@RequestMapping("/upload")
public class CloudinaryController {

	private CloudinaryService cloudinaryService;

	public CloudinaryController(CloudinaryService cloudinaryService) {
		this.cloudinaryService = cloudinaryService;
	}

	@DeleteMapping("/image-blog")
	public ResponseEntity<?> deleteImage(@RequestParam String publicId) throws ServiceException {

		cloudinaryService.delete(publicId);

		ApiResponse<String> apiResponse = new ApiResponse<>(ResponseStatus.DELETED.getHttpStatusCode(),
				"/api/blog/image-blog?publicId=" + publicId, MethodEnum.DELETE, "Image deleted successfully", "Success",
				false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping("/image-blog")
	public ResponseEntity<?> upload(@RequestParam("image") MultipartFile file) throws ServiceException {
		ImageResponseCloudinaryDTO response = cloudinaryService.upload(file, "blog_profile_spring");

		ApiResponse<ImageResponseCloudinaryDTO> apiResponse = new ApiResponse<>(
				ResponseStatus.CREATED.getHttpStatusCode(), "/api/blog/image-blog", MethodEnum.POST,
				"Image uploaded successfully", response, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}

}
