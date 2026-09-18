package com.mx.dev.blog.spring_001_blog;

import java.util.List;

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

	Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

	List<String> keys = List.of("DB_URL", "DB_USERNAME", "DB_PASSWORD", "S3_ACCESS_KEY", "S3_SECRET_ACCESS_KEY",
		"S3_REGION", "CLOUDINARY_CLOUD_NAME", "CLOUDINARY_API_KEY", "CLOUDINARY_API_SECRET", "MAILTRAP_HOST",
		"MAILTRAP_PORT", "MAILTRAP_USER", "MAILTRAP_PASS");

	for (String key : keys) {
	    String value = dotenv.get(key);
	    if (value != null) {
		System.setProperty(key, value);
	    }
	}

	SpringApplication.run(BlogDevSpringApp.class, args);
    }

}
