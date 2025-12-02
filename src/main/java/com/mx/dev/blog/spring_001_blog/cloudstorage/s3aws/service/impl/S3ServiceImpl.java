package com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseCloudinaryDTO;
import com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.repository.S3Repository;
import com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.service.S3Service;

@Service
public class S3ServiceImpl implements S3Service {

	private S3Repository s3Repository;

	public S3ServiceImpl(S3Repository s3Repository) {
		this.s3Repository = s3Repository;
	}

	@Override
	public void deleteObject(String fileKey) {
		s3Repository.deleteObject(fileKey);
	}

	// upload a file
	@Override
	public ImageResponseCloudinaryDTO uploadFile(String folder, MultipartFile file) {

		// 1. convert file
		File fileObj = convertMultiPartFileToFile(file);

		// 2. set a name
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

		// 3. get response from s3 service
		Map<String, java.lang.Object> res = s3Repository.uploadFile(folder, folder + fileName, fileObj);

		// 4. build
		ImageResponseCloudinaryDTO imageResponseCloudinaryDTO = new ImageResponseCloudinaryDTO(
				res.get("url").toString(), Double.parseDouble((String) res.get("sizeBytes")), res);

		return imageResponseCloudinaryDTO;
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			// log.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}

}
