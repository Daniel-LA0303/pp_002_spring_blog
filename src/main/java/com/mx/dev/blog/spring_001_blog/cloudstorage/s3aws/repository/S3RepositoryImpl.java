package com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Repository
public class S3RepositoryImpl implements S3Repository {

	private final S3Client s3Client;

	public S3RepositoryImpl(S3Client s3Client) {
		this.s3Client = s3Client;
	}

	// delete a object in bucket
	@Override
	public void deleteObject(String fileKey) {

		// create request
		DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket("spring-react-blog-s3")
				.key(fileKey).build();

		// delete object
		s3Client.deleteObject(deleteObjectRequest);
	}

	// upload a file
	@Override
	public Map<String, java.lang.Object> uploadFile(String folder, String fileName, File fileObj) {
		try {
			// 1. upload a file (img)
			PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket("spring-react-blog-s3")
					.key(folder + "/" + fileName).build();

			// 2. get response
			PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromFile(fileObj));

			long sizeBytes = fileObj.length();

			// 2. generate a URL
			URL fileUrl = s3Client.utilities()
					.getUrl(builder -> builder.bucket("spring-react-blog-s3").key(folder + "/" + fileName));

			// 3. delete file from local
			Files.delete(fileObj.toPath());

			// 4.return meta data
			Map<String, java.lang.Object> responseData = new HashMap<>();
			responseData.put("fileName", fileName);
			responseData.put("eTag", response.eTag());
			responseData.put("versionId", response.versionId() != null ? response.versionId() : "null");
			responseData.put("bucketName", "spring-react-blog-s3");
			responseData.put("fileKey", folder + "/" + fileName);
			responseData.put("statusCode", String.valueOf(response.sdkHttpResponse().statusCode()));
			responseData.put("url", fileUrl.toString()); // URL del archivo
			responseData.put("sizeBytes", String.valueOf(sizeBytes));

			return responseData;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to upload file", e);
		}
	}

}
