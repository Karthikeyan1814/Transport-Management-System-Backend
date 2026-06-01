package com.example.transport_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.transport_backend.entity.BusRoute;

public interface BusRouteRepo extends JpaRepository<BusRoute, Long> {
	
}
