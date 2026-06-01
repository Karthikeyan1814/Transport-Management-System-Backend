package com.example.transport_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport_backend.Repo.BusInfoRepo;
import com.example.transport_backend.Repo.BusRouteRepo;
import com.example.transport_backend.Repo.MessageRepo;
import com.example.transport_backend.Repo.StaffRepo;
import com.example.transport_backend.Repo.StudentRepo;
import com.example.transport_backend.Repo.StudentReportRepository;
import com.example.transport_backend.entity.BusRoute;
import com.example.transport_backend.entity.FormDetails;
import com.example.transport_backend.entity.Message;
import com.example.transport_backend.entity.MessageRequest;
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
@CrossOrigin(origins = { "http://127.0.0.1:5500", "http://localhost:5500", "http://localhost:5173/","http://localhost:5173/Admin",
		"http://localhost:8080/Staffreq","http://localhost:5173"})
public class FormController {

	@Autowired
	BusInfoRepo Busrepo;

	@Autowired
	StudentRepo StdRepo;

	@Autowired
	StaffRepo Staffrepo;
	
	@Autowired
	MessageRepo messagerepo;
	
    @Autowired
    BusRouteRepo routeRepo;
    
    @Autowired
    private StudentReportRepository reportRepository;
    
    @Autowired
 MessageRepo messageRepository;


	@PostMapping("/Savebusdetails")
	public String savedbus(
	        @RequestParam String busnumber,
	        @RequestParam String modelname,
	        @RequestParam String numberplate,
	        @RequestParam String usage,
	        @RequestParam String status,
	        @RequestParam MultipartFile image,
	        @RequestParam MultipartFile document
	) throws Exception {

	    String uploadDir = "uploads/";

	    // unique filenames
	    String imgName = System.currentTimeMillis()+"_"+image.getOriginalFilename();
	    String docName = System.currentTimeMillis()+"_"+document.getOriginalFilename();

	    Path imgPath = Paths.get(uploadDir + imgName);
	    Path docPath = Paths.get(uploadDir + docName);

	    Files.createDirectories(imgPath.getParent());

	    Files.write(imgPath, image.getBytes());
	    Files.write(docPath, document.getBytes());

	    FormDetails bus = new FormDetails();

	    bus.setBusnumber(busnumber);
	    bus.setModelname(modelname);
	    bus.setNumberplate(numberplate);
	    bus.setUsage(usage);
	    bus.setStatus(status);

	    // store only URL/path in DB
	    bus.setImage("/uploads/" + imgName);
	    bus.setDocument("/uploads/" + docName);

	    Busrepo.save(bus);
	    
	    System.out.println("Saving to: " + imgPath.toAbsolutePath());
	    return "Bus Detailed Saved Successfully ✅";
	}

	@Override
	public String toString() {
		return "FormController [Busrepo=" + Busrepo + "]";
	}

	@PostMapping("/SaveStudentDetails")
	public String savestd(@RequestBody StudentForm Studentdetails) {
		StdRepo.save(Studentdetails);
		System.out.println(Studentdetails);
		return ("student details ....");
	}

	@PostMapping("/logdetails")
	 public String logdt(@RequestParam String domain,
            @RequestParam String email,
            @RequestParam String password) {

		Optional<StudentForm> DetailOpt = null;
		Optional<Staffdetail> StaffOpt = null;
			if ("user".equalsIgnoreCase(domain)) {
				DetailOpt=StdRepo.findByEmail(email);
				if (DetailOpt.isPresent()) {
					StudentForm student = DetailOpt.get();
					if(student.getStatus().equalsIgnoreCase("Approved")) {
						if (student.getPassword().equals(password)) {
							return "student login";
						} else {
							return "Invalid password";
						}
					}else if(student.getStatus().equalsIgnoreCase("Rejected")) {
						return "Sorry Your Application Has Been Rejected";
					}else {
						return "Your Application Is Under Reviewing , Pls Be wait or Try After Sometimes Later";
					}
				}
				else return "Invalid Email";
				
			    }
				else if("admin".equalsIgnoreCase(domain)) {
					if("admin@123".equals(email) && "admin".equals(password)) {
						return "admin login";
				}
				}
					
				else if("staff".equalsIgnoreCase(domain)) {
						StaffOpt =Staffrepo.findByEmail(email);
						if (StaffOpt.isPresent()) {
							Staffdetail staff = StaffOpt.get();
							if(staff.getStatus().equalsIgnoreCase("Approved")) {
								if (staff.getPassword().equals(password)) {
									return "staff login";
								} else {
									return "Invalid password";
								}
							}else if(staff.getStatus().equalsIgnoreCase("Rejected")) {
								return "Sorry Your Application Has Been Rejected";
							}else {
								return "Your Application Is Under Reviewing , Pls Be wait or Try After Sometimes Later";
							}
							
						}else return "Invalid Email";
					}
					return "Invalid domain";
				}

	@PostMapping("/SaveStaffDetail")
	public String staffdet(@RequestBody Staffdetail staffdetail) {
		Staffrepo.save(staffdetail);
		return "Staff detail saved";
	}
	
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
	
	@GetMapping("/viewbus")
	public List<FormDetails> viewbus(){
		
		return Busrepo.findAll();
	}
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
	        report.setFromRole(fromRole != null ? fromRole : role);
	        report.setSenderEmail(senderEmail);
	        report.setTarget(target);
	        report.setTitle(title);
	        report.setDescription(description);
	        report.setCreatedAt(LocalDateTime.now());

	        if (image != null && !image.isEmpty()) {
	            report.setImage(image.getBytes());
	            report.setImageContentType(image.getContentType());
	        }

	        reportRepository.save(report);
	        return ResponseEntity.ok("Report submitted successfully");
	    }
	    
	    
	    
	 // --- MESSAGE APIs ---

	    @PostMapping("/CreateNewMessage")
	    public ResponseEntity<String> createNewMessage(@RequestBody MessageRequest payload) {

	        Message m = new Message();

	        // who sent it
	        m.setRole(payload.getRole());
	        m.setFromRole(payload.getFrom() != null ? payload.getFrom() : payload.getRole());
	        m.setSenderEmail(payload.getSenderEmail());

	        // normalize audience: "all", "staff", "admin", "student"
	        String audience = firstNonNull(
	                payload.getAudience(),
	                payload.getTarget(),
	                payload.getDomain(),
	                "all"
	        );
	        m.setAudience(audience);

	        // content/message – frontend may send both
	        String content = firstNonNull(payload.getContent(), payload.getMessage(), "");
	        m.setContent(content);

	        m.setImportant(Boolean.TRUE.equals(payload.getImportant()));
	        m.setCreatedAt(LocalDateTime.now());

	        messageRepository.save(m);
	        return ResponseEntity.ok("Message saved");
	    }

	    private String firstNonNull(String... values) {
	        for (String v : values) {
	            if (v != null && !v.isBlank()) return v;
	        }
	        return null;
	    }
	    
	    @GetMapping("/messages")
	    public List<Message> getMessages(@RequestParam String role) {
	        // role will be "admin", "staff" or "student" from frontend
	        String normalizedRole = role.toLowerCase();

	        List<Message> all = messageRepository.findAll();

	        return all.stream()
	                .filter(m -> {
	                    String aud = m.getAudience();
	                    if (aud == null || aud.isBlank()) aud = "all";
	                    aud = aud.toLowerCase();

	                    // show messages sent to everyone OR specifically to this role
	                    return aud.equals("all") || aud.equals(normalizedRole)
	                           // also allow comma‑separated audiences if you ever use them ("admin,staff")
	                           || aud.startsWith(normalizedRole + ",")
	                           || aud.endsWith("," + normalizedRole)
	                           || aud.contains("," + normalizedRole + ",");
	                })
	                .toList();
	    }
	
	
}
