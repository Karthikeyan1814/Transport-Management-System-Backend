package com.example.transport_backend.Repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transport_backend.entity.StudentForm;


public interface StudentRepo extends JpaRepository<StudentForm, Integer>{


    StudentForm findByEmail(String email);
		
	
}
