package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums;

public enum OwnerTypeStorage {
	USER("user_profile_spring"), BLOG("blog_profile_spring"), CATEGORY("category_images");

	private final String folder;

	OwnerTypeStorage(String folder) {
		this.folder = folder;
	}

	public String getFolder() {
		return folder;
	}
}
