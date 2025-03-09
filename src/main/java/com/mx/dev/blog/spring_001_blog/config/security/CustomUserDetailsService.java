package com.mx.dev.blog.spring_001_blog.config.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.user.entities.RoleEntity;
import com.mx.dev.blog.spring_001_blog.user.entities.UserEntity;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserService userService;

	public CustomUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity;
		try {
			// Cambiamos la lógica para buscar por email en lugar de username
			userEntity = userService.getOneUserByEmailOrThrow(email); // Aquí debe ser un método que busque por email

			System.out.println("***************");
			System.out.println(userEntity.getEmail());
		} catch (ServiceException e) {
			throw new UsernameNotFoundException("User not found with email: " + email, e);
		}

		if (userEntity == null || userEntity.getRoles() == null) {
			throw new UsernameNotFoundException("User or roles not found for email: " + email);
		}

		return new User(userEntity.getEmail(), userEntity.getPassword(), getAuthorities(userEntity.getRoles()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Set<RoleEntity> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

}
