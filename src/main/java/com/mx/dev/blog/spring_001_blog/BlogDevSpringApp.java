package com.mx.dev.blog.spring_001_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.cors.CorsConfigurationSource;

import com.mx.dev.blog.spring_001_blog.config.security.CustomUserDetailsService;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
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

		System.out.println("************ AWS S3 ***************");
		System.out.println(dotenv.get("S3_ACCESS_KEY"));
		System.out.println(dotenv.get("S3_SECRET_ACCESS_KEY"));
		System.out.println(dotenv.get("S3_REGION"));

		System.out.println("************ CLOUDINARY ***************");
		System.out.println(dotenv.get("CLOUDINARY_CLOUD_NAME"));
		System.out.println(dotenv.get("CLOUDINARY_API_KEY"));
		System.out.println(dotenv.get("CLOUDINARY_API_SECRET"));

		SpringApplication.run(BlogDevSpringApp.class, args);
	}

}
