package com.mx.dev.blog.spring_001_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.cors.CorsConfigurationSource;

import com.mx.dev.blog.spring_001_blog.config.security.CustomUserDetailsService;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
//@EnableAsync
@EnableJpaAuditing
public class BlogDevSpringApp {

	private final CustomUserDetailsService customUserDetailsService;

	private final CorsConfigurationSource corsConfigurationSource;

	BlogDevSpringApp(CorsConfigurationSource corsConfigurationSource,
			CustomUserDetailsService customUserDetailsService) {
		this.corsConfigurationSource = corsConfigurationSource;
		this.customUserDetailsService = customUserDetailsService;
	}

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().load();

		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		System.setProperty("S3_ACCESS_KEY", dotenv.get("S3_ACCESS_KEY"));
		System.setProperty("S3_SECRET_ACCESS_KEY", dotenv.get("S3_SECRET_ACCESS_KEY"));
		System.setProperty("S3_REGION", dotenv.get("S3_REGION"));

		System.setProperty("CLOUDINARY_CLOUD_NAME", dotenv.get("CLOUDINARY_CLOUD_NAME"));
		System.setProperty("CLOUDINARY_API_KEY", dotenv.get("CLOUDINARY_API_KEY"));
		System.setProperty("CLOUDINARY_API_SECRET", dotenv.get("CLOUDINARY_API_SECRET"));

		System.setProperty("MAILTRAP_HOST", dotenv.get("MAILTRAP_HOST"));
		System.setProperty("MAILTRAP_PORT", dotenv.get("MAILTRAP_PORT"));
		System.setProperty("MAILTRAP_USER", dotenv.get("MAILTRAP_USER"));
		System.setProperty("MAILTRAP_PASS", dotenv.get("MAILTRAP_PASS"));

		SpringApplication.run(BlogDevSpringApp.class, args);
	}

}
