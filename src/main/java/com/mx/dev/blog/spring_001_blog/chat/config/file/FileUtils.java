package com.mx.dev.blog.spring_001_blog.chat.config.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

	private FileUtils() {
	}

	public static byte[] readFileFromLocation(String fileUrl) {
		// return empty byte array if file url is null or blank
		if (StringUtils.isBlank(fileUrl)) {
			return new byte[0];
		}
		try {
			// convert file url to path and read all bytes from the file
			Path filePath = new File(fileUrl).toPath();
			return Files.readAllBytes(filePath);
		} catch (IOException e) {
			// log warning if file cannot be read from the specified path
			log.warn("no file found in the path {}", fileUrl);
		}
		// return empty byte array if file reading fails
		return new byte[0];
	}
}