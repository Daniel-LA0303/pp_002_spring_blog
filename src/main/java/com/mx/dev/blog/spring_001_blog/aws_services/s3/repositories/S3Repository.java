package com.mx.dev.blog.spring_001_blog.aws_services.s3.repositories;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public interface S3Repository {

	String checkIfBucketExist(String bucketName);

	String createBucket(String bucketName);

	void deleteObject(String bucketName, String fileKey);

	byte[] downloadFile(String bucketName, String fileName) throws IOException;

	List<String> getAllBuckets();

	ResponseInputStream<GetObjectResponse> getObject(String bucketName, String fileName) throws IOException;

	List<Object> listObjectsInBucket(String bucket);

	void moveObject(String bucketName, String fileKey, String destinationFileKey);

	Map<String, String> uploadFile(String bucketName, String fileName, File fileObj);

}
