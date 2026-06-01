package com.example.transport_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transport_backend.entity.FormDetails;

@Repository
public interface BusInfoRepo extends JpaRepository<FormDetails, Integer>{
	
}
