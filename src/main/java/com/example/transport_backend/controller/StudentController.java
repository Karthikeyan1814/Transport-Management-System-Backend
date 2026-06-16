package com.example.transport_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport_backend.Repo.StudentRepo;
import com.example.transport_backend.entity.Bus;
import com.example.transport_backend.entity.Organization;
import com.example.transport_backend.entity.StudentForm;
import com.example.transport_backend.wrapper.AuthUser;
import com.example.transport_backend.wrapper.CustomUserDetails;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/profile")
    public Object profile(
    		//with AuthenticationPrinciple annotation 
    		
            @AuthenticationPrincipal   // this is security context holder like it have the current user details 
            CustomUserDetails user
//@AuthenticationPrincipal is simply a shortcut that grabs the Principal from the current authenticated request and injects it into your controller method. 🚀
            
    ) {

        return user.getStudent();
    }
    

   @GetMapping("/getStudent")
   public ResponseEntity<Object> getStudent(
		   @AuthenticationPrincipal
		   AuthUser user) {
	   
	   StudentForm student=studentRepo.findByEmail(user.getUsername());
//	   Organization org=student.getOrg();
//	   Bus bus=student.getBus();
	   Map<String,Object> response=new HashMap<String, Object>();
	   response.put("status", "success");
	   response.put("student", student);
//	   response.put("org", org);
//	   response.put("bus", bus);
	   return ResponseEntity.ok(response);
   }
}
    
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

 

    		