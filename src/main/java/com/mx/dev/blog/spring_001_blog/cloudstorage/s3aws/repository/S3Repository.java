package com.mx.dev.blog.spring_001_blog.cloudstorage.s3aws.repository;

import java.io.File;
import java.util.Map;

public interface S3Repository {

	void deleteObject(String fileKey);

	Map<String, java.lang.Object> uploadFile(String folder, String fileName, File fileObj);

}
