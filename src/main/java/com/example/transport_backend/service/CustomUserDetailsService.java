package com.example.transport_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.transport_backend.entity.Organization;
import com.example.transport_backend.entity.Staffdetail;
import com.example.transport_backend.entity.StudentForm;
import com.example.transport_backend.wrapper.AuthUser;
import com.example.transport_backend.Repo.OrganizationRepo;
import com.example.transport_backend.Repo.StaffRepo;
import com.example.transport_backend.Repo.StudentRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private  StudentRepo stdRepo;
	
	@Autowired
	private StaffRepo staffRepo;
	@Autowired
	private OrganizationRepo orgRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) {
	       StudentForm student =
	                stdRepo.findByEmail(email);

	        if(student != null) {

	            return new AuthUser(
	                    student.getSid(),
	                    student.getEmail(),
	                    student.getPassword(),
	                    student.getDomain()
	            );
	        }

	        Staffdetail staff =
	                staffRepo.findByEmail(email);

	        if(staff != null) {

	            return new AuthUser(
	                    staff.getId(),
	                    staff.getEmail(),
	                    staff.getPassword(),
	                    staff.getRole()
	            );
	        }
	        
	        Organization org=orgRepo.findByEmail(email);
	        if(org !=null) {
	        	return new AuthUser(
	        			org.getId(),
	        			org.getEmail(),
	        			org.getPassword(),
	        			org.getRole());
	        }

	        throw new UsernameNotFoundException(
	                "User Not Found"
	        );
	    }
	//this method is check the user is actually present or not 
	//if it present it return the data other wise it return 404 or user name not found
	// This method is called by (JWTFilter)
	
}
