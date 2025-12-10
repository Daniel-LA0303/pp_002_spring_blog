package com.mx.dev.blog.spring_001_blog.chat.config.file;

import static java.io.File.separator;
import static java.lang.System.currentTimeMillis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private static final Logger log = LoggerFactory.getLogger(FileService.class);

	@Value("${application.file.uploads.media-output-path}")
	private String fileUploadPath;

	public FileService() {
	}

	// save file in local
	public String saveFile(MultipartFile sourceFile, Long userId) {
		final String fileUploadSubPath = "users" + separator + userId;
		return uploadFile(sourceFile, fileUploadSubPath);
	}

	// get file extension
	private String getFileExtension(String fileName) {
		if (fileName == null || fileName.isEmpty()) {
			return "";
		}
		int lastDotIndex = fileName.lastIndexOf(".");
		if (lastDotIndex == -1) {
			return "";
		}
		return fileName.substring(lastDotIndex + 1).toLowerCase();
	}

	// upload file and get a link
	private String uploadFile(MultipartFile sourceFile, String fileUploadSubPath) {

		// construct the final upload path by combining base path and subdirectory
		final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
		File targetFolder = new File(finalUploadPath);

		// check if folder exists, create if it doesn't
		if (!targetFolder.exists()) {
			boolean folderCreated = targetFolder.mkdirs();
			if (!folderCreated) {
				log.warn("failed to create the target folder: " + targetFolder);
				return null;
			}
		}

		// get file extension from original filename
		final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());

		// generate unique filename using current timestamp and original extension
		String targetFilePath = finalUploadPath + separator + currentTimeMillis() + "." + fileExtension;
		Path targetPath = Paths.get(targetFilePath);

		// write file to the target location
		try {
			Files.write(targetPath, sourceFile.getBytes());
			log.info("file saved to: " + targetFilePath);
			return targetFilePath;
		} catch (IOException e) {
			log.error("file was not saved", e);
		}
		return null;
	}
}