package com.example.transport_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transport_backend.entity.Stop;

public interface BusStopsRepo extends JpaRepository<Stop, Integer> {

	Stop findByRoute(int route);
}
