package com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.service.CloudinaryService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseCloudinaryDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

	private final Cloudinary cloudinary;

	public CloudinaryServiceImpl(Cloudinary cloudinary) {
		this.cloudinary = cloudinary;
	}

	@Override
	public void delete(String publicId) throws ServiceException {
		try {
			// 1. delete image from cloudinary
			Map<String, Object> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

			String deleteResult = result.get("result").toString();

			if (!deleteResult.equals("ok")) {
				throw new ServiceException(String.format("Cloudinary delete error: %s", deleteResult),
						ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/upload/image-blog", MethodEnum.DELETE);
			}

		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), ResponseStatus.INTERNAL_SERVER_ERROR.getHttpStatusCode(),
					"/upload/image-blog", MethodEnum.DELETE);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public ImageResponseCloudinaryDTO upload(MultipartFile file, String folder) throws ServiceException {

		try {

			// 1. upload image
			Map<String, Object> resultUpload = cloudinary.uploader().upload(file.getBytes(),
					ObjectUtils.asMap("folder", folder));

			// 2. build metadata
			Map<String, Object> metadata = new HashMap<>();
			metadata.put("format", resultUpload.get("format"));
			metadata.put("width", resultUpload.get("width"));
			metadata.put("height", resultUpload.get("height"));
			metadata.put("public_id", resultUpload.get("public_id"));
			metadata.put("resource_type", resultUpload.get("resource_type"));
			metadata.put("created_at", resultUpload.get("created_at"));

			// 3. pass from bytes to megabytes
			Double sizeBytes = resultUpload.get("bytes") != null ? Double.valueOf(resultUpload.get("bytes").toString())
					: 0.0;
			Double sizeMB = sizeBytes / (1024 * 1024);

			// 4. build response
			ImageResponseCloudinaryDTO res = new ImageResponseCloudinaryDTO(resultUpload.get("secure_url").toString(),
					sizeMB, metadata);

			return res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), ResponseStatus.BAD_REQUEST.getHttpStatusCode(),
					"/upload/image-blog", MethodEnum.POST);
		}
	}
}
