package com.example.transport_backend.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transport_backend.entity.Staffdetail;


@Repository
public interface StaffRepo extends JpaRepository<Staffdetail, Integer>{

	 Staffdetail findByEmail(String Email) ;
}
