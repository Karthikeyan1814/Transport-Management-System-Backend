package com.example.transport_backend.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport_backend.wrapper.CustomUserDetails;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/profile")
    public Object profile(
    		//with AuthenticationPrinciple annotation 
    		
            @AuthenticationPrincipal   // this is security context holder like it have the current user details 
            CustomUserDetails user
//@AuthenticationPrincipal is simply a shortcut that grabs the Principal from the current authenticated request and injects it into your controller method. 🚀
            
    ) {

        return user.getStudent();
    }}
    
    
    // without authentication
   // Authentication auth =
//
//    		SecurityContextHolder
//    		.getContext()
//    		.getAuthentication();
//
//    		CustomUserDetails user =
//
//    		(CustomUserDetails)
//    		auth.getPrincipal();
//
//    		return user.getStudent();
//}

    		