package com.mx.dev.blog.spring_001_blog.auth.services.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.email")
public class EmailProperties {

	private String from;

	private String frontendUrl;

	// Getters y Setters
	public String getFrom() {
		return from;
	}

	public String getFrontendUrl() {
		return frontendUrl;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setFrontendUrl(String frontendUrl) {
		this.frontendUrl = frontendUrl;
	}
}