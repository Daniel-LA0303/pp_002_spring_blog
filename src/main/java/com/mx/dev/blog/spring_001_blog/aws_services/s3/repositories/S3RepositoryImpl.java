package com.mx.dev.blog.spring_001_blog.aws_services.s3.repositories;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

@Repository
public class S3RepositoryImpl implements S3Repository {

	private final S3Client s3Client;

	@Autowired
	public S3RepositoryImpl(S3Client s3Client) {
		this.s3Client = s3Client;
	}

	@Override
	public String checkIfBucketExist(String bucketName) {
		try {
			this.s3Client.headBucket(headBucket -> headBucket.bucket(bucketName));
			return "El bucket " + bucketName + " si existe.";
		} catch (S3Exception exception) {
			return "El bucket " + bucketName + " no existe.";
		}
	}

	@Override
	public String createBucket(String bucketName) {
		CreateBucketResponse response = this.s3Client.createBucket(bucketBuilder -> bucketBuilder.bucket(bucketName));
		return "Bucket creado en la ubicación: " + response.location();
	}

	/**
	 * delete a object from our bucket
	 */
	@Override
	public void deleteObject(String bucketName, String fileKey) {

		// create request
		DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket(bucketName).key(fileKey).build();

		// delete object
		s3Client.deleteObject(deleteObjectRequest);
	}

	/**
	 * download a object from our bucket
	 */
	@Override
	public byte[] downloadFile(String bucketName, String fileName) {
		GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(fileName).build();

		try (ResponseInputStream<GetObjectResponse> s3ObjectInputStream = s3Client.getObject(getObjectRequest)) {
			return s3ObjectInputStream.readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getAllBuckets() {
		ListBucketsResponse bucketsResponse = this.s3Client.listBuckets();

		if (bucketsResponse.hasBuckets()) {
			return bucketsResponse.buckets().stream().map(Bucket::name).toList();
		} else {
			return List.of();
		}
	}

	/**
	 * get a object from our bucket
	 */
	@Override
	public ResponseInputStream<GetObjectResponse> getObject(String bucketName, String fileName) throws IOException {
		GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(fileName).build();
		return s3Client.getObject(getObjectRequest);
	}

	/**
	 * get all object in one bucket
	 */
	@Override
	public List<Object> listObjectsInBucket(String bucket) {
		ListObjectsV2Request listObjectsReqManual = ListObjectsV2Request.builder().bucket(bucket).build();
		ListObjectsV2Response listObjectsV2Response = s3Client.listObjectsV2(listObjectsReqManual);

		List<Object> items = listObjectsV2Response.contents().stream().parallel().map(S3Object::key)
				.map(key -> mapS3ToObject(bucket, key)).collect(Collectors.toList());
		return items;
	}

	/**
	 * do a copy to another destination, so finally delete the object
	 */
	@Override
	public void moveObject(String bucketName, String fileKey, String destinationFileKey) {
		CopyObjectRequest copyObjRequest = CopyObjectRequest.builder().sourceBucket(bucketName).sourceKey(fileKey)
				.destinationBucket(bucketName).destinationKey(destinationFileKey).build();

		s3Client.copyObject(copyObjRequest);
		deleteObject(bucketName, fileKey);
	}

	/**
	 * upload a file to our bucket in a package in our bucket in this part url will
	 * be save in a DB
	 */
	@Override
	public Map<String, String> uploadFile(String bucketName, String fileName, File fileObj) {
		try {
			// 1. Subir el archivo a S3
			PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName)
					.key("profiles_pictures_blog/" + fileName).build();

			PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromFile(fileObj));

			// 2. Generar la URL del archivo subido
			URL fileUrl = s3Client.utilities()
					.getUrl(builder -> builder.bucket(bucketName).key("profiles_pictures_blog/" + fileName));

			// 3. Eliminar el archivo local
			Files.delete(fileObj.toPath());

			// 4. Retornar la información en un Map
			Map<String, String> responseData = new HashMap<>();
			responseData.put("fileName", fileName);
			responseData.put("eTag", response.eTag());
			responseData.put("versionId", response.versionId() != null ? response.versionId() : "null");
			responseData.put("bucketName", bucketName);
			responseData.put("key", "profiles_pictures_blog/" + fileName);
			responseData.put("statusCode", String.valueOf(response.sdkHttpResponse().statusCode()));
			responseData.put("url", fileUrl.toString()); // URL del archivo

			return responseData;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to upload file", e);
		}
	}

	/**
	 * this method help us to generate url s3 to all elements or object from a
	 * bucket
	 * 
	 * @param bucket
	 * @param key
	 * @return
	 */
	private Object mapS3ToObject(String bucket, String key) {

		// get metadata
		HeadObjectResponse metadata = s3Client.headObject(HeadObjectRequest.builder().bucket(bucket).key(key).build());

		// get name
		String name = metadata.metadata().get("name");

		// Generate url s3
		URL url = s3Client.utilities().getUrl(builder -> builder.bucket(bucket).key(key));

		// generate and return a object instance
		return new com.mx.dev.blog.spring_001_blog.utils.dtos.servicesAWS.s3.Object(name, key, url);
	}

	/*
	 * @Override
	 * 
	 * // this method create a link with time to upload a file public String
	 * generatePresignedUploadUrl(String bucketName, String key, Duration duration)
	 * { PutObjectRequest putObjectRequest = PutObjectRequest.builder()
	 * .bucket(bucketName) .key(key) .build();
	 * 
	 * PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
	 * .signatureDuration(duration) .putObjectRequest(putObjectRequest) .build();
	 * 
	 * PresignedPutObjectRequest presignedRequest =
	 * this.s3Presigner.presignPutObject(presignRequest); URL presignedUrl =
	 * presignedRequest.url();
	 * 
	 * return presignedUrl.toString(); }
	 * 
	 * // this method create a link with time to download a file
	 * 
	 * @Override public String generatePresignedDownloadUrl(String bucketName,
	 * String key, Duration duration) { GetObjectRequest getObjectRequest =
	 * GetObjectRequest.builder() .bucket(bucketName) .key(key) .build();
	 * 
	 * GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
	 * .signatureDuration(duration) .getObjectRequest(getObjectRequest) .build();
	 * 
	 * PresignedGetObjectRequest presignedRequest =
	 * this.s3Presigner.presignGetObject(presignRequest); URL presignedUrl =
	 * presignedRequest.url();
	 * 
	 * return presignedUrl.toString(); }
	 */

}
