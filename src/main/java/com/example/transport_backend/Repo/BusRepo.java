package com.example.transport_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transport_backend.entity.Bus;



@Repository
public interface BusRepo extends JpaRepository<Bus, Integer>{
	
}
