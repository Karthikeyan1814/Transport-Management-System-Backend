package com.example.transport_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.transport_backend.Repo.BusRouteRepo;

import com.example.transport_backend.Repo.OrganizationRepo;
import com.example.transport_backend.Repo.StaffRepo;
import com.example.transport_backend.Repo.StudentRepo;
import com.example.transport_backend.Repo.StudentReportRepository;
import com.example.transport_backend.entity.BusRoute;

import com.example.transport_backend.entity.Organization;
import com.example.transport_backend.entity.Staffdetail;
import com.example.transport_backend.entity.Stop;
import com.example.transport_backend.entity.StudentForm;
import com.example.transport_backend.entity.StudentReport;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.io.IOException;

import java.time.LocalDateTime;


@RestController
public class FormController {

	

	@Autowired
	StudentRepo StdRepo;

	@Autowired
	StaffRepo Staffrepo;
	
	@Autowired
	OrganizationRepo OrgRepo;
	
	
    @Autowired
    BusRouteRepo routeRepo;
 
    
    
    
    @Autowired
    private StudentReportRepository reportRepository;
    

    
    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;


//	@PostMapping("/Savebusdetails")
//	public String savedbus(
//	        @RequestParam String busnumber,
//	        @RequestParam String modelname,
//	        @RequestParam String numberplate,
//	        @RequestParam String usage,
//	        @RequestParam String status,
//	        @RequestParam MultipartFile image,
//	        @RequestParam MultipartFile document
//	) throws Exception {
//
//	    String uploadDir = "uploads/";
//
//	    // unique filenames
//	    String imgName = System.currentTimeMillis()+"_"+image.getOriginalFilename();
//	    String docName = System.currentTimeMillis()+"_"+document.getOriginalFilename();
//
//	    Path imgPath = Paths.get(uploadDir + imgName);
//	    Path docPath = Paths.get(uploadDir + docName);
//
//	    Files.createDirectories(imgPath.getParent());
//
//	    Files.write(imgPath, image.getBytes());
//	    Files.write(docPath, document.getBytes());
//
//	    FormDetails bus = new FormDetails();
//
//	    bus.setBusnumber(busnumber);
//	    bus.setModelname(modelname);
//	    bus.setNumberplate(numberplate);
//	    bus.setUsage(usage);
//	    bus.setStatus(status);
//
//	    // store only URL/path in DB
//	    bus.setImage("/uploads/" + imgName);
//	    bus.setDocument("/uploads/" + docName);
//
//	    Busrepo.save(bus);
//	    
//	    System.out.println("Saving to: " + imgPath.toAbsolutePath());
//	    return "Bus Detailed Saved Successfully ✅";
//	}

//	@Override
//	public String toString() {
//		return "FormController [Busrepo=" + Busrepo + "]";
//	}

	
	@PutMapping("/Changeapprove")
	public String chgapr(@RequestParam int id) {
		Optional<Staffdetail> staff=Staffrepo.findById(id);
		if(staff.isPresent()) {
			Staffdetail staffdata=staff.get();
			staffdata.setStatus("Approved");
			Staffrepo.save(staffdata);
			return "Approved";
		}
		return "Somthing Wrong";
	}
	
	@PutMapping("/Changereject")
	public String chgreg(@RequestParam int id) {
		Optional<Staffdetail> staff=Staffrepo.findById(id);
		if(staff.isPresent()) {
			Staffdetail staffdata=staff.get();
			staffdata.setStatus("Rejected");
			Staffrepo.save(staffdata);
			return "Rejected";
		}
		return "Somthing Wrong";
	}
	
	@PutMapping("/ChangeStdapprove")
	public String chgstdapr(@RequestParam int id) {
		Optional<StudentForm> student=StdRepo.findById(id);
		if(student.isPresent()) {
			StudentForm studentdetail=student.get();
			studentdetail.setStatus("Approved");
			StdRepo.save(studentdetail);
			return "Approved";
		}
		return "Somthing Wrong";
	}
	
	@PutMapping("/ChangeStdreject")
	public String chgstdreg(@RequestParam int id) {
		Optional<StudentForm> student=StdRepo.findById(id);
		if(student.isPresent()) {
			StudentForm studentdetail=student.get();
			studentdetail.setStatus("Rejected");
			StdRepo.save(studentdetail);
			return "Rejected";
		}
		return "Somthing Wrong";
	}
	
	
	
	@GetMapping("/Studentreq")
	public List<StudentForm> stdreq(){
		return StdRepo.findAll();
	}
	
	@GetMapping("/Staffreq")
	public List<Staffdetail> staffreq(){
		return Staffrepo.findAll();
	}
	
//	@GetMapping("/viewbus")
//	public List<FormDetails> viewbus(){
//		
//		return Busrepo.findAll();
//	}
	 @PostMapping("/saveRoute")
	    public String saveRoute(@RequestBody BusRoute route){

	        // attach parent reference to each stop
	        for(Stop s : route.getStops()){
	            s.setRoute(route);
	        }

	        routeRepo.save(route);

	        return "Route saved successfully ✅";
	    }
	 
	 @GetMapping("/getroute")
	 public List<BusRoute>  getroute(){
		 return routeRepo.findAll();
	 }
	 
	  // === GET REPORTS (for Admin/Staff dashboard) ===
	 @GetMapping("/reports")
	 public List<StudentReport> getReports(
	         @RequestParam String role,                // "admin" or "staff"
	         @RequestParam(required = false) Boolean resolved
	 ) {
	     String normalizedRole = role.toLowerCase();

	     List<StudentReport> all;
	     if (resolved != null) {
	         all = reportRepository.findByResolved(resolved);
	     } else {
	         all = reportRepository.findAll();
	     }

	     return all.stream()
	             .filter(r -> {
	                 String target = r.getTarget();
	                 if (target == null || target.isBlank()) target = "both";
	                 target = target.toLowerCase();

	                 // show reports for "both" or exactly this role
	                 return target.equals("both") || target.equals(normalizedRole);
	             })
	             .toList();
	 }
	    
	    // === CREATE REPORT (used by React: POST /CreateReport with FormData) ===
	    @PostMapping("/CreateReport")
	    public ResponseEntity<String> createReport(
	            @RequestParam String role,
	            @RequestParam(name = "from", required = false) String fromRole,
	            @RequestParam(required = false) String senderEmail,
	            @RequestParam String target,
	            @RequestParam String title,
	            @RequestParam String description,
	            @RequestPart(name = "image", required = false) MultipartFile image
	    ) throws IOException {

	        StudentReport report = new StudentReport();
	        report.setRole(role);
	
	        report.setSenderEmail(senderEmail);
	        report.setTarget(target);
	        report.setTitle(title);
	        report.setDescription(description);
	        report.setCreatedAt(LocalDateTime.now());

	       

	        reportRepository.save(report);
	        return ResponseEntity.ok("Report submitted successfully");
	    }
	    
	    
	    
	
	
	
	
}
