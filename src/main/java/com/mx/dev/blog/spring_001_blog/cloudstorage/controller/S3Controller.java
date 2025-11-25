package com.mx.dev.blog.spring_001_blog.cloudstorage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mx.dev.blog.spring_001_blog.cloudstorage.service.S3Service;
import com.mx.dev.blog.spring_001_blog.cloudstorage.utils.dto.Object;

@RestController
@RequestMapping(value = "/s3-service")
public class S3Controller {

	private S3Service s3Service;

	public S3Controller(S3Service s3Service) {
		this.s3Service = s3Service;
	}

	// Verificar si un bucket existe
	@GetMapping("/{bucketName}/exists")
	public String checkIfBucketExist(@PathVariable String bucketName) {
		return s3Service.checkIfBucketExist(bucketName);
	}

	// Crear un nuevo bucket
	@PostMapping("/{bucketName}/create")
	public String createBucket(@PathVariable String bucketName) {
		return s3Service.createBucket(bucketName);
	}

	@DeleteMapping("/deleteObject")
	public ResponseEntity<String> deleteFile(@RequestParam(value = "bucketName") String bucketName,
			@RequestParam(value = "fileName") String fileName) {
		s3Service.deleteObject(bucketName, fileName);
		return new ResponseEntity<>("File deleted", HttpStatus.OK);
	}

	@GetMapping("/downloadS3File")
	public ResponseEntity<ByteArrayResource> downloadS3File(@RequestParam(value = "bucketName") String bucketName,
			@RequestParam(value = "fileName") String fileName) throws IOException {
		byte[] data = s3Service.downloadFile(bucketName, fileName);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok().contentLength(data.length).header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" + fileName + "\"").body(resource);
	}

	@GetMapping
	public List<String> getAllBuckets() {
		return s3Service.getAllBuckets();
	}

	// yes
	@GetMapping("/getS3FileContent")
	public ResponseEntity<String> getS3FileContent(@RequestParam(value = "bucketName") String bucketName,
			@RequestParam(value = "fileName") String fileName) throws IOException {
		return new ResponseEntity<>(s3Service.getS3FileContent(bucketName, fileName), HttpStatus.OK);
	}

	@GetMapping("/listS3Files")
	public ResponseEntity<List<Object>> getS3Files(@RequestParam(value = "bucketName") String bucketName)
			throws IOException {
		List<Object> list = new ArrayList<>();
		HttpStatus status = HttpStatus.OK;
		try {
			list = s3Service.getS3Files(bucketName);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(list, status);
	}

	@GetMapping("/moveFile")
	public ResponseEntity<String> moveFile(@RequestParam(value = "bucketName") String bucketName,
			@RequestParam(value = "fileName") String fileKey,
			@RequestParam(value = "fileNameDest") String fileKeyDest) {
		s3Service.moveObject(bucketName, fileKey, fileKeyDest);
		return new ResponseEntity<>("File moved", HttpStatus.OK);
	}

	@PostMapping("/uploadFile")
	public ResponseEntity<Map<String, String>> uploadFile(@RequestParam(value = "bucketName") String bucketName,
			@RequestParam(value = "filePath") String filePath, @RequestParam(value = "file") MultipartFile file) {
		return new ResponseEntity<>(s3Service.uploadFile(bucketName, filePath, file), HttpStatus.OK);
	}

	@PostMapping("/uploadFiles")
	public ResponseEntity<String> uploadFiles(@RequestParam(value = "bucketName") String bucketName,
			@RequestParam(value = "filePath") String filePath,
			@RequestParam(value = "files") List<MultipartFile> files) {
		String response = s3Service.uploadFiles(bucketName, filePath, files);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
