package com.mx.dev.blog.spring_001_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BlogDevSpringApp {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().load();

		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("S3_ACCESS_KEY", dotenv.get("S3_ACCESS_KEY"));
		System.setProperty("S3_SECRET_ACCESS_KEY", dotenv.get("S3_SECRET_ACCESS_KEY"));
		System.setProperty("S3_REGION", dotenv.get("S3_REGION"));

		SpringApplication.run(BlogDevSpringApp.class, args);
	}

}
