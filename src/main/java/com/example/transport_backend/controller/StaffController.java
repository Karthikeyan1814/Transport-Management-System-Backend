package com.example.transport_backend.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport_backend.Repo.BusInchargeRepo;
import com.example.transport_backend.Repo.OrganizationRepo;
import com.example.transport_backend.Repo.StaffRepo;
import com.example.transport_backend.entity.BusIncharge;
import com.example.transport_backend.entity.Organization;
import com.example.transport_backend.entity.Staffdetail;


@RestController
@RequestMapping("/Staff")
public class StaffController {

	@Autowired
	private BusInchargeRepo inchargeRepo;
	
	@Autowired
	private StaffRepo staffRepo;
	
	@Autowired
	private OrganizationRepo orgRepo;
	
	@PostMapping("/saveIncharge")
	public ResponseEntity<Object> saveIncharge(@RequestBody BusIncharge staff){
		BusIncharge response=inchargeRepo.save(staff);
		 Map<String,Object> result = new HashMap<>();
		    result.put("status", "success");
		    result.put("data", response);

		    return ResponseEntity.ok(result);
	}
	
	@PostMapping("/saveStaff")
	public ResponseEntity<Object> saveStaffs(@RequestBody Staffdetail staff){
		System.out.println(staff);
		
		 Map<String,Object> result = new HashMap<>();
		
		Staffdetail response=staffRepo.save(staff);
		

		    result.put("status", "success");
		    result.put("data", response);

		    return ResponseEntity.ok(result);
	}
}
