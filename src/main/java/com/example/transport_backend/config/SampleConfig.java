package com.example.transport_backend.config;

import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.transport_backend.security.JwtFilter;



public class SampleConfig {

	private final JwtFilter jwtfilter;
	
	public SampleConfig(JwtFilter jwtfilter) {
		this.jwtfilter=jwtfilter;
	}
	
	@Bean
	SecurityFilterChain securityfilterchain(HttpSecurity http) {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth ->{
					auth.requestMatchers("/signin").permitAll();
					auth.anyRequest().authenticated();
				})
				.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
				.build();
			
	}
}
