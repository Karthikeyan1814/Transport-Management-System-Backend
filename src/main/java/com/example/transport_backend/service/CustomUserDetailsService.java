package com.example.transport_backend.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.transport_backend.entity.StudentForm;
import com.example.transport_backend.wrapper.CustomUserDetails;
import com.example.transport_backend.Repo.StudentRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final StudentRepo repo;
	public CustomUserDetailsService(StudentRepo repo) {
		this.repo=repo;
	}
	
	public UserDetails loadUserByUsername(String email) {
		StudentForm student=repo.findByEmail(email);
		if(student ==null) {
			throw new UsernameNotFoundException("User Name Not Found");
		}
		return new CustomUserDetails(student);
	}
	
}
