package com.mx.dev.blog.spring_001_blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mx.dev.blog.spring_001_blog.utils.constants.ExceptionsConstants;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.GlobalExcepction;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@SpringBootTest
class Spring001BlogApplicationTests {

	@Test
	void handleException_returnsInternalServerError() {
		GlobalExcepction globalExcepction = new GlobalExcepction();

		Exception ex = new RuntimeException("Test error");

		ResponseEntity<?> response = globalExcepction.handleException(ex);

		assertNotNull(response);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

		ApiResponse<?> body = (ApiResponse<?>) response.getBody();
		assertNotNull(body);
		assertEquals(500, body.getStatus());
		assertTrue(body.getError());
		assertTrue(body.getMessage().contains("Test error"));
	}

	@Test
	void mainRuns() {
		BlogDevSpringApp.main(new String[] {});
	}

	@Test
	void utilityConstructorIsPrivateAndThrowsExceptionsConstants() throws Exception {
		Constructor<ExceptionsConstants> ctor = ExceptionsConstants.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(ctor.getModifiers()), "El constructor debe ser private");

		ctor.setAccessible(true);
		InvocationTargetException ex = assertThrows(InvocationTargetException.class, ctor::newInstance);

		assertTrue(ex.getTargetException() instanceof IllegalStateException);
		assertEquals("Utility class", ex.getTargetException().getMessage());
	}
}
