package com.mx.dev.blog.spring_001_blog.config;

import static org.mockito.Mockito.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;

@TestConfiguration
@Profile("test")
public class TestMailConfig {

	@Bean
	public JavaMailSender javaMailSender() {

		return mock(JavaMailSender.class);
	}
}
