package com.example.transport_backend.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transport_backend.entity.Organization;

public interface OrganizationRepo extends JpaRepository<Organization, Integer> {

	Organization findByEmail(String email);
}
