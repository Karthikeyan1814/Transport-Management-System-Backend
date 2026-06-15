package com.example.transport_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transport_backend.entity.ImportantMessage;

public interface ImportanMsgRepo extends JpaRepository<ImportantMessage, Integer> {

}
