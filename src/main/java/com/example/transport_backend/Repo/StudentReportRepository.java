package com.example.transport_backend.Repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.transport_backend.entity.StudentReport;

public interface StudentReportRepository extends JpaRepository<StudentReport, Integer> {

	 List<StudentReport> findBySenderEmail(String senderEmail);

	    List<StudentReport> findByTarget(String target);

	    List<StudentReport> findByResolved(boolean resolved);
	    
	    List<StudentReport> findByOrganization_Id(Integer orgId);
}