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

            @AuthenticationPrincipal
            CustomUserDetails user

    ) {

        return user.getStudent();
    }
}
