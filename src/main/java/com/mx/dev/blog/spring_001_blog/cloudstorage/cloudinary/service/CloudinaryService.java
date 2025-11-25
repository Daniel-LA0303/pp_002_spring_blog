package com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.service;

import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface CloudinaryService {

	void delete(String publicId) throws ServiceException;

	ImageResponseDTO upload(MultipartFile file) throws ServiceException;

}
