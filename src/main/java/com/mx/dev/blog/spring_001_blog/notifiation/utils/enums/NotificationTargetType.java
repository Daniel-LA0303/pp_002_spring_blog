package com.mx.dev.blog.spring_001_blog.notifiation.utils.enums;

public enum NotificationTargetType {
	BLOG("BLOG"), USER("USER"), COMMENT("COMMENT"), REPLY("REPLY");

	private final String text;

	NotificationTargetType(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
