package com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.service.CloudinaryService;
import com.mx.dev.blog.spring_001_blog.cloudstorage.cloudinary.utils.dto.ImageResponseDTO;
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
			// Puedes enviar más opciones si quieres, aquí usamos emptyMap()
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
	public ImageResponseDTO upload(MultipartFile file) throws ServiceException {
		List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "webp", "avif");

		String extensions = null;

		if (file.getOriginalFilename() != null) {
			String[] splitName = file.getOriginalFilename().split("\\.");
			extensions = splitName[splitName.length - 1];
		}

		if (!allowedExtensions.contains(extensions)) {
			// throw new BodyNotValidException(String.format("Extension %s not allowed.",
			// extensions));

			throw new ServiceException(String.format("Extension %s not allowed.", extensions),
					ResponseStatus.BAD_REQUEST.getHttpStatusCode(), "/upload/image-blog", MethodEnum.POST);
		}

		try {

			Map<String, Object> resultUpload = cloudinary.uploader().upload(file.getBytes(),
					ObjectUtils.asMap("folder", "blog_profile_spring"));

			String imageUrl = resultUpload.get("secure_url").toString();
			String publicId = resultUpload.get("public_id").toString();

			ImageResponseDTO res = new ImageResponseDTO(imageUrl, publicId);

			return res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), ResponseStatus.BAD_REQUEST.getHttpStatusCode(),
					"/upload/image-blog", MethodEnum.POST);
		}
	}

}
