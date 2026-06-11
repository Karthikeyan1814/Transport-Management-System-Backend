package com.example.transport_backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
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

    public AuthController(
            StudentRepo studentRepository,
            JwtUtil jwtUtil
    ) {
        this.studentRepository = studentRepository;
        this.jwtUtil = jwtUtil;
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

        if (!student.getPassword()
                .equals(
                        request.getPassword()
                )) {

            return ResponseEntity
                    .badRequest()
                    .body("Invalid Password");
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