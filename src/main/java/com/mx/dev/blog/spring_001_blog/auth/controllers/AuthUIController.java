package com.mx.dev.blog.spring_001_blog.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth-ui")
public class AuthUIController {

	@GetMapping("/auth-category-form")
	public String showCategoryFormUI() {

		return "category-form.html";
	}

	@GetMapping("/auth-home")
	public String showHomeUI() {

		return "home-auth.html";
	}

	@GetMapping("/auth-login")
	public String showLoginUI() {

		return "login.html";
	}

}
