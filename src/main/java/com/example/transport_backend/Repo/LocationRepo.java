package com.example.transport_backend.Repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transport_backend.entity.Location;

public interface LocationRepo extends JpaRepository<Location, Integer>{

}
