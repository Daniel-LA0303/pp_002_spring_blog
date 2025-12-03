package com.mx.dev.blog.spring_001_blog.serach.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.dev.blog.spring_001_blog.blog.services.blog.BlogService;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.category.services.CategoryService;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.BlogsByCategoryInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.search.MultipleSearchDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserInfoCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.response.ApiResponse;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping()
	public ResponseEntity<?> search(@RequestParam String query, @RequestParam(required = false) List<String> modules,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		MultipleSearchDTO response = new MultipleSearchDTO();

		if (modules == null || modules.contains("categories")) {
			Page<BlogsByCategoryInfoDTO> categoriesPage = categoryService.searchCategories(query, page, size);
			System.out.println("Categories: " + categoriesPage.getContent()); // Imprime las categorías
			response.setCategories(categoriesPage);
		}
		if (modules == null || modules.contains("blogs")) {
			Page<BlogInfoCardDTO> blogsPage = blogService.searchBlogs(query, page, size);
			System.out.println("Blogs: " + blogsPage.getContent()); // Imprime los blogs
			response.setBlogs(blogsPage);
		}
		if (modules == null || modules.contains("users")) {
			Page<UserInfoCardDTO> usersPage = userService.searchUsers(query, page, size);
			System.out.println("Users: " + usersPage.getContent()); // Imprime los usuarios
			response.setUsers(usersPage);
		}

		// Imprime la respuesta completa antes de devolverla
		System.out.println("Full Response: " + response);

		ApiResponse<MultipleSearchDTO> apiResponse = new ApiResponse<>(ResponseStatus.SUCCESS.getHttpStatusCode(),
				"/api/search", MethodEnum.GET, "Search by param", response, false);

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		// return ResponseEntity.ok(response);
	}

}
