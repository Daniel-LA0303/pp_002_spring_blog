package com.mx.dev.blog.spring_001_blog.cloudstorage.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.repository.S3Repository;
import com.mx.dev.blog.spring_001_blog.cloudstorage.service.S3Service;
import com.mx.dev.blog.spring_001_blog.cloudstorage.utils.dto.Object;

@Service
public class S3ServiceImpl implements S3Service {

	private S3Repository s3Repository;

	public S3ServiceImpl(S3Repository s3Repository) {
		this.s3Repository = s3Repository;
	}

	private static String getAsString(InputStream is) throws IOException {
		if (is == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			is.close();
		}
		return sb.toString();
	}

	@Override
	public String checkIfBucketExist(String bucketName) {
		return s3Repository.checkIfBucketExist(bucketName);
	}

	@Override
	public String createBucket(String bucketName) {
		return s3Repository.createBucket(bucketName);
	}

	@Override
	public void deleteObject(String bucketName, String fileKey) {
		s3Repository.deleteObject(bucketName, fileKey);
	}

	@Override
	public byte[] downloadFile(String bucketName, String fileName) throws IOException {
		return s3Repository.downloadFile(bucketName, fileName);
	}

	@Override
	public List<String> getAllBuckets() {
		return s3Repository.getAllBuckets();
	}

	@Override
	public String getS3FileContent(String bucketName, String fileName) throws IOException {
		return getAsString(s3Repository.getObject(bucketName, fileName));
	}

	@Override
	public List<Object> getS3Files(String bucketName) throws IOException {
		return s3Repository.listObjectsInBucket(bucketName);
	}

	@Override
	public void moveObject(String bucketName, String fileKey, String destinationFileKey) {
		s3Repository.moveObject(bucketName, fileKey, destinationFileKey);
	}

	@Override
	public Map<String, String> uploadFile(String bucketName, String filePath, MultipartFile file) {
		File fileObj = convertMultiPartFileToFile(file);
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		return s3Repository.uploadFile(bucketName, filePath + fileName, fileObj);
	}

	@Override
	public String uploadFiles(String bucketName, String filePath, List<MultipartFile> files) {
		StringBuilder response = new StringBuilder();
		for (MultipartFile file : files) {
			response.append(uploadFile(bucketName, filePath, file)).append("\n");
		}
		return response.toString();
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
