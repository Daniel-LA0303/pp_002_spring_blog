package com.mx.dev.blog.spring_001_blog.cloudstorage.storageservices.utils.enums;

public enum CategoryStorage {
	AVATAR("avatar"), BLOG_COVER("blog_cover"), BLOG_IMAGE("blog_image");

	private final String category;

	CategoryStorage(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
}
