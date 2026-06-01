package com.example.transport_backend.Repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.transport_backend.entity.StudentReport;

public interface StudentReportRepository extends JpaRepository<StudentReport, Integer> {

    List<StudentReport> findByTargetIgnoreCase(String target);

    List<StudentReport> findByResolved(Boolean resolved);

    List<StudentReport> findByTargetIgnoreCaseAndResolved(String target, Boolean resolved);
}