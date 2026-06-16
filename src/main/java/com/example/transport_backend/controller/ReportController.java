package com.example.transport_backend.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.transport_backend.Repo.OrganizationRepo;
import com.example.transport_backend.Repo.StaffRepo;
import com.example.transport_backend.Repo.StudentRepo;
import com.example.transport_backend.Repo.StudentReportRepository;
import com.example.transport_backend.entity.Organization;
import com.example.transport_backend.entity.Staffdetail;
import com.example.transport_backend.entity.StudentForm;
import com.example.transport_backend.entity.StudentReport;
import com.example.transport_backend.wrapper.AuthUser;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final StudentReportRepository reportRepo;

    @Autowired
    private StudentRepo stdRepo;
    
    @Autowired
    private OrganizationRepo orgRepo;
    
    @Autowired
    private StaffRepo staffRepo;
    
    public ReportController(StudentReportRepository reportRepo) {
        this.reportRepo = reportRepo;
    }

    // ==========================
    // STUDENT SEND REPORT
    // ==========================

    @PostMapping("/send")
    public ResponseEntity<?> sendReport(
            @RequestBody StudentReport report,
            @AuthenticationPrincipal AuthUser user) {

        if (!"student".equals(user.getRole())) {
            return ResponseEntity.badRequest()
                    .body("Only Students Can Create Reports");
        }
        
        StudentForm std=stdRepo.findByEmail(user.getUsername());

        report.setRole("student");
        report.setSenderEmail(user.getUsername());
        report.setCreatedAt(LocalDateTime.now());
        report.setOrganization(std.getOrg());
        report.setResolved(false);

        StudentReport saved = reportRepo.save(report);

        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("data", saved);

        return ResponseEntity.ok(result);
    }

    // ==========================
    // STUDENT VIEW OWN REPORTS
    // ==========================

    @GetMapping("/myReports")
    public ResponseEntity<?> myReports(
            @AuthenticationPrincipal AuthUser user) {

        List<StudentReport> reports =
                reportRepo.findBySenderEmail(user.getUsername());

        return ResponseEntity.ok(reports);
    }

    // ==========================
    // STAFF & ORGANIZATION VIEW ALL
    // ==========================

    @GetMapping("/organizationReports")
    public ResponseEntity<?> getOrganizationReports(
            @AuthenticationPrincipal AuthUser user) {

        if (!"organization".equals(user.getRole())) {
            return ResponseEntity.badRequest()
                    .body("Access Denied");
        }

        Organization org =
                orgRepo.findByEmail(user.getUsername());

        List<StudentReport> reports =
                reportRepo.findByOrganization_Id(org.getId());

        return ResponseEntity.ok(reports);
    }

    // ==========================
    // MARK REPORT RESOLVED
    // ==========================

    @PutMapping("/resolve/{id}")
    public ResponseEntity<?> resolveReport(
            @PathVariable Integer id,
            @AuthenticationPrincipal AuthUser user) {

        String role = user.getRole();

        if (!"staff".equals(role)
                && !"organization".equals(role)) {

            return ResponseEntity.badRequest()
                    .body("Access Denied");
        }

        StudentReport report =
                reportRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Report Not Found"));

        report.setResolved(true);

        return ResponseEntity.ok(
                reportRepo.save(report));
    }
    
    @GetMapping("/staffReports")
    public ResponseEntity<?> getStaffReports(
            @AuthenticationPrincipal AuthUser user) {

        Staffdetail staff =
                staffRepo.findByEmail(user.getUsername());

        List<StudentReport> reports =
                reportRepo.findByOrganization_Id(
                        staff.getOrg().getId());

        return ResponseEntity.ok(reports);
    }
}
