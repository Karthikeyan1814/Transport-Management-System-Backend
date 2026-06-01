package com.example.transport_backend.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transport_backend.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // for /messages?role=admin
    List<Message> findByRoleIgnoreCase(String role);

    // for /messages?audience=staff
    List<Message> findByAudienceIgnoreCase(String audience);

    // for /messages?role=admin&audience=staff
    List<Message> findByRoleIgnoreCaseAndAudienceIgnoreCase(String role, String audience);
}