package com.example.transport_backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transport_backend.entity.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Integer> {

	// Filter by role (case insensitive)
    List<Message> findByRoleIgnoreCase(String role);

    // Filter by audience (case insensitive)
    List<Message> findByAudienceIgnoreCase(String audience);

    // Filter by both role AND audience (case insensitive)
    List<Message> findByRoleIgnoreCaseAndAudienceIgnoreCase(String role, String audience);
}

