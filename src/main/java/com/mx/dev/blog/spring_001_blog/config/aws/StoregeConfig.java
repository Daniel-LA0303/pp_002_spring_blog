package com.mx.dev.blog.spring_001_blog.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class StoregeConfig {

	@Value("${app.cloud.s3.accessKey}")
	private String accessKey;

	@Value("${app.cloud.s3.secretAccessKey}")
	private String accessSecret;

	@Value("${app.cloud.s3.region}")
	private String region;

	@Bean
	public S3Client s3Client() {
		AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, accessSecret);

		return S3Client.builder().credentialsProvider(StaticCredentialsProvider.create(credentials))
				.region(Region.of(region)).build();
	}

}
