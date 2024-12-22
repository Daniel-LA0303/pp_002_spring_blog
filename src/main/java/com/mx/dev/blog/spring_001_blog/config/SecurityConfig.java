package com.mx.dev.blog.spring_001_blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mx.dev.blog.spring_001_blog.config.security.CustomUserDetailsService;
import com.mx.dev.blog.spring_001_blog.config.security.JwtAuthenticationEntryPonit;
import com.mx.dev.blog.spring_001_blog.config.security.JwtAuthenticationFilter;

//estas configuraciones son para habilitar la seguridad en la aplicacion
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtAuthenticationEntryPonit jwtAuthenticationEntryPoint;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.csrf().disable().exceptionHandling().authenticationEntryPoint(
		 * jwtAuthenticationEntryPoint).and()
		 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 * // STATELESS
		 * 
		 * .and().authorizeRequests().antMatchers().permitAll().antMatchers(
		 * "/api/auth/**").permitAll() .anyRequest().authenticated();
		 * 
		 * http.addFilterBefore(jwtAuthenticationFilter(),
		 * UsernamePasswordAuthenticationFilter.class);
		 */

		http.csrf().disable().authorizeRequests().anyRequest().permitAll(); // -> test
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
