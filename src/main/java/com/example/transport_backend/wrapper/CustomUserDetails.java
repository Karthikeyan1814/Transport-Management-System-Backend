package com.example.transport_backend.wrapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.SimpleIdGenerator;

import com.example.transport_backend.entity.StudentForm;

public class CustomUserDetails implements UserDetails {

	public StudentForm student;
	
	public CustomUserDetails(StudentForm student2) {
		this.student=student2;
	}
	
	public StudentForm getStudent() {
		return student;
	}
	
	public String getUsername() {
		return student.getEmail();
	}
	public String getPassword() {
		return student.getPassword();
	}
	
	
	public Collection<GrantedAuthority> getAuthorities(){
		return List.of(
				new SimpleGrantedAuthority(
						"ROLE_"+student.getDomain())
				);
	} // this method return the role of the user 
	@Override
	public boolean
	isAccountNonExpired(){
	    return true;
	}

	@Override
	public boolean
	isAccountNonLocked(){
	    return true;
	}

	@Override
	public boolean
	isCredentialsNonExpired(){
	    return true;
	}

	@Override
	public boolean
	isEnabled(){
	    return true;
	}

	
}
