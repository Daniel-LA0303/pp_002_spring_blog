package com.mx.dev.blog.spring_001_blog.notifiation.utils.enums;

public enum NotificationType {
	LIKE("LIKE"), COMMENT("COMMENT"), SHARE("SHARE"), NEW_POST("NEW_POST");

	private final String text;

	NotificationType(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
