package com.example.transport_backend.config;

import org.springframework.context.annotation.*;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

					auth.requestMatchers("/auth/login","/Signup/saveStudent","/Signup/saveStaff","/Signup/saveOrganization","/RouteX"
							,"/ManageBus/saveBus","/ManageMessage/sendImpMessage","/ManageBus/saveBus","/ManageBus/getBus","/Staff/saveStaff","/ManageBus/RequestLocation","/Staff/saveIncharge","/Organization/saveOrganization").permitAll();

					auth.anyRequest().authenticated();

				})

				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

				.build();

	}
	
	@Bean
	PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}

}