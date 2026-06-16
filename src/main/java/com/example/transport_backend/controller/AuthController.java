package com.example.transport_backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport_backend.Repo.OrganizationRepo;
import com.example.transport_backend.Repo.StaffRepo;
import com.example.transport_backend.Repo.StudentRepo;
import com.example.transport_backend.dto.LoginRequest;
import com.example.transport_backend.entity.Organization;
import com.example.transport_backend.entity.Staffdetail;
import com.example.transport_backend.entity.Staffdetail;
import com.example.transport_backend.entity.StudentForm;
import com.example.transport_backend.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final StudentRepo studentRepository;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordencoder;
	private final StaffRepo staffRepo;
	private final OrganizationRepo orgRepo;

	public AuthController(StudentRepo studentRepository, JwtUtil jwtUtil, PasswordEncoder encoder, StaffRepo staff,
			OrganizationRepo org) {
		this.studentRepository = studentRepository;
		this.jwtUtil = jwtUtil;
		this.passwordencoder = encoder;
		this.staffRepo = staff;
		this.orgRepo = org;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

		StudentForm student = studentRepository.findByEmail(request.getEmail());

		if (student != null) {
			if (!passwordencoder.matches(request.getPassword(), student.getPassword())) {
				return ResponseEntity.badRequest().body("Ivalid Password or Username");

			}
			if (student.getStatus().equals("Rejected")) {
				return ResponseEntity.badRequest().body("Your Applicatrion is Rejected , Try After SomeTimes");
			}
			if (!student.getStatus().equals("Approved")) {
				return ResponseEntity.badRequest().body("Your Application is UnderWaiting");
			}
			String token = jwtUtil.generateToken(student.getEmail(), student.getDomain(), student.getSid());
			System.out.println("GENERATED TOKEN = " + token);
			return ResponseEntity.ok(Map.of("token", token, "user", student));
		}

		Staffdetail staff = staffRepo.findByEmail(request.getEmail());

		if (staff != null) {

			if (!passwordencoder.matches(request.getPassword(), staff.getPassword())) {

				return ResponseEntity.badRequest().body("Invalid Username or Password");
			}
			if (!staff.getStatus().equals("Approved")) {
				return ResponseEntity.badRequest().body("Your Application is UnderWaiting By Organization");
			}

			if (staff.getStatus().equals("Rejected")) {
				return ResponseEntity.badRequest().body("Your Applicatrion is Rejected , Try After SomeTimes");
			}
			String token = jwtUtil.generateToken(staff.getEmail(), staff.getRole(), staff.getId());

			return ResponseEntity.ok(Map.of("token", token, "role", staff.getRole(), "user", staff));
		}
		
		Organization org=orgRepo.findByEmail(request.getEmail());
		if(org !=null) {
			if (!passwordencoder.matches(request.getPassword(), org.getPassword())) {

				return ResponseEntity.badRequest().body("Invalid Username or Password");
			}

			String token = jwtUtil.generateToken(org.getEmail(), org.getRole(), org.getId());

			return ResponseEntity.ok(Map.of("token", token, "role", org.getRole(), "user", org));
		}
		
		

		return ResponseEntity.badRequest().body("User Not Found");
	}
}

//
//        if (!passwordencoder.matches(request.getPassword(),student.getPassword())) { ////matches(rawPassword, encodedPassword)
//
//            return ResponseEntity
//                    .badRequest()
//                    .body("Invalid Username or Password");
//        }
//        
//        if (!student.getStatus().equals("Approved")) {
//
//            return ResponseEntity
//                    .badRequest()
//                    .body("Your Are Not Approved Yet , Please Be Wait : )");
//        }
//
//        String token =
//                jwtUtil.generateToken(
//                        student.getEmail(),
//                        student.getDomain(),
//                        student.getSid()
//                );
//
//        System.out.println(
//        	    "GENERATED TOKEN = " + token
//        	);
//        return ResponseEntity.ok(
//                Map.of(
//                        "token", token,
//                        "user", student
//                )
//        );
//    }
