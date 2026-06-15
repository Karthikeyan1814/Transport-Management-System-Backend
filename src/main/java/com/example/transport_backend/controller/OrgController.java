package com.example.transport_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport_backend.Repo.OrganizationRepo;
import com.example.transport_backend.entity.Organization;

@RestController
@RequestMapping("/Organization")
public class OrgController {
	
	@Autowired
	private OrganizationRepo orgRepo;

	@PostMapping("/saveOrganization")
	public ResponseEntity<Object> saveOrganization(@RequestBody Organization org){
		
		Organization response=orgRepo.save(org);
		 Map<String,Object> result = new HashMap<>();

		    result.put("status", "success");
		    result.put("data", response);
		    return ResponseEntity.ok(result);
	}
}
