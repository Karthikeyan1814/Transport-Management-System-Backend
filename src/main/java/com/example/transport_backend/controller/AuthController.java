package com.example.transport_backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport_backend.Repo.StudentRepo;
import com.example.transport_backend.dto.LoginRequest;
import com.example.transport_backend.entity.StudentForm;
import com.example.transport_backend.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final StudentRepo studentRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordencoder;

    public AuthController(
            StudentRepo studentRepository,
            JwtUtil jwtUtil,
            PasswordEncoder encoder
    ) {
        this.studentRepository = studentRepository;
        this.jwtUtil = jwtUtil;
        this.passwordencoder=encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {

        StudentForm student =
                studentRepository
                .findByEmail(
                        request.getEmail()
                );

        if (student == null) {

            return ResponseEntity
                    .badRequest()
                    .body("User Not Found");
        }

        if (!passwordencoder.matches(request.getPassword(),student.getPassword())) { ////matches(rawPassword, encodedPassword)

            return ResponseEntity
                    .badRequest()
                    .body("Invalid Username or Password");
        }
        
        if (!student.getStatus().equals("Approved")) {

            return ResponseEntity
                    .badRequest()
                    .body("Your Are Not Approved Yet , Please Be Wait : )");
        }

        String token =
                jwtUtil.generateToken(
                        student.getEmail(),
                        student.getDomain()
                );

        System.out.println(
        	    "GENERATED TOKEN = " + token
        	);
        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "user", student
                )
        );
    }
}