package com.example.transport_backend.config;

import org.springframework.context.annotation.*;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.*;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.transport_backend.security.JwtFilter;

@Configuration

public class SecurityConfig {

	private final JwtFilter jwtFilter;

	public SecurityConfig(JwtFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}

	@Bean

	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http

				.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> {

					auth.requestMatchers("/auth/login").permitAll();

					auth.anyRequest().authenticated();

				})

				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

				.build();

	}

}