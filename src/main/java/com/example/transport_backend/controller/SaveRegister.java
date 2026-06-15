package com.example.transport_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport_backend.Repo.BusRepo;
import com.example.transport_backend.Repo.OrganizationRepo;
import com.example.transport_backend.Repo.StaffRepo;
import com.example.transport_backend.Repo.StudentRepo;
import com.example.transport_backend.entity.Bus;
import com.example.transport_backend.entity.Organization;
import com.example.transport_backend.entity.Staffdetail;
import com.example.transport_backend.entity.StudentForm;

@RestController
@RequestMapping("/Signup")
public class SaveRegister {

	   
    private final PasswordEncoder passwordencoder;
    
    @Autowired
    private StudentRepo StdRepo;
    
    @Autowired
    private StaffRepo Staffrepo;
    
    @Autowired
    private OrganizationRepo OrgRepo;
    
    @Autowired
    private BusRepo busRepo;
    
    public SaveRegister(PasswordEncoder encoder) {
    	this.passwordencoder=encoder;
    }
    
    @PostMapping("/saveStudent")
	public ResponseEntity<Map<String,Object>> saveStudent(@RequestBody StudentForm std){
		Map<String,Object> response=new HashMap<>();
		try {
			std.setPassword(passwordencoder.encode(std.getPassword()));
			Organization org=OrgRepo.findById(std.getOrgId()).orElseThrow();
			std.setOrg(org);
			Bus bus=busRepo.findById(std.getBusId()).orElseThrow();
			std.setBus(bus);
			StdRepo.save(std);
		}catch(Error e) {
			response.put("error",e);
			return ResponseEntity.badRequest().body(response);
		}
	     String name=std.getFirstname()+" "+std.getLastname();
		response.put("status", "success");
		response.put("context", "Hello "+name+" Your Registration is Successfull , Please be wait your mentor grand the access once they reviewed details , You Will be notify through Whatsapp !!!" );
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/saveStaff")
	public ResponseEntity<Map<String,Object>> saveStaff(@RequestBody Staffdetail staff){
		Map<String,Object> response=new HashMap<>();
		try {
			staff.setPassword(passwordencoder.encode(staff.getPassword()));
			Staffrepo.save(staff);
		}catch(Error e) {
			response.put("error",e);
			return ResponseEntity.badRequest().body(response);
		}
	     String name=staff.getFirstname()+" "+staff.getLastname();
		response.put("status", "success");
		response.put("context", "Hello "+name+" Your Registration is Successfull , Please be wait your Organization grand the access once they reviewed details , You Will be notify through Whatsapp !!!" );
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping("/saveOrganization")
	public ResponseEntity<Map<String,Object>> saveOrganization(@RequestBody Organization org){
		Map<String,Object> response=new HashMap<>();
		try {
		    org.setPassword(passwordencoder.encode(org.getPassword()));
		    
			OrgRepo.save(org);
		}catch(Error e) {
			response.put("error",e);
			response.put("status","failed");
			return ResponseEntity.badRequest().body(response);
		}
	     String name=org.getName();
		response.put("status", "success");
		response.put("context", "Hello "+name+" Your Registration is Successfull , your Organization is officialy registed to this platform , Welcome To Route X " );
		return ResponseEntity.ok(response);
	}

	
}
