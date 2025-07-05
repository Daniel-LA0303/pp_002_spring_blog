package com.mx.dev.blog.spring_001_blog.aws_services.s3.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

	String checkIfBucketExist(String bucketName);

	String createBucket(String bucketName);

	void deleteObject(String bucketName, String fileKey);

	byte[] downloadFile(String bucketName, String fileName) throws IOException;

	List<String> getAllBuckets();

	String getS3FileContent(String bucketName, String fileName) throws IOException;

	List<Object> getS3Files(String bucketName) throws IOException;

	void moveObject(String bucketName, String fileKey, String destinationFileKey);

	Map<String, String> uploadFile(String bucketName, String filePath, MultipartFile file);

	String uploadFiles(String bucketName, String filePath, List<MultipartFile> files);

}
