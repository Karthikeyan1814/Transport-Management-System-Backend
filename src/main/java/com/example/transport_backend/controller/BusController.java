package com.example.transport_backend.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport_backend.Repo.BusRepo;
import com.example.transport_backend.Repo.BusRouteRepo;
import com.example.transport_backend.Repo.LocationRepo;
import com.example.transport_backend.Repo.OrganizationRepo;
import com.example.transport_backend.Repo.StaffRepo;
import com.example.transport_backend.Repo.StudentRepo;
import com.example.transport_backend.entity.Bus;
import com.example.transport_backend.entity.BusRoute;
import com.example.transport_backend.entity.Location;
import com.example.transport_backend.entity.Organization;
import com.example.transport_backend.entity.Staffdetail;
import com.example.transport_backend.entity.Stop;
import com.example.transport_backend.entity.StudentForm;
import com.example.transport_backend.wrapper.AuthUser;

@RestController
@RequestMapping("/ManageBus")
public class BusController {

	@Autowired
	private BusRouteRepo routeRepo;

	@Autowired
	private BusRepo busRepo;
	
	@Autowired
	private LocationRepo locationRepo;
	
	@Autowired
	private StudentRepo stdRepo;
	
	@Autowired
	private StaffRepo staffRepo;
	
	@Autowired
	private OrganizationRepo orgRepo;
	
	// Save the details of Routes 
	@PostMapping("/saveRoute")
	public ResponseEntity<Object> saveRoute(
	        @RequestBody BusRoute route){

	    for(Stop stop : route.getStops()) {
	        stop.setRoute(route);
	    }

	    BusRoute response = routeRepo.save(route);

	    Map<String,Object> result = new HashMap<>();

	    result.put("status", "success");
	    result.put("data", response);

	    return ResponseEntity.ok(result);
	}
	
	//save the details of busses
	@PostMapping("/saveBus")
	public ResponseEntity<Object> saveBus(@RequestBody Bus bus){
		Bus response=busRepo.save(bus);
		 Map<String,Object> result = new HashMap<>();

		    result.put("status", "success");
		    result.put("data", response);

		    return ResponseEntity.ok(result);
	}
	
	@PostMapping("/RequestLocation")
	public ResponseEntity<Object> saveLocation(
	        @RequestBody Location location,
	        @AuthenticationPrincipal AuthUser user) {

	    Bus bus = busRepo.findById(location.getBusid())
	            .orElseThrow(() -> new RuntimeException("Bus Not Found"));

	    if ("student".equals(user.getRole())) {

	        StudentForm std =
	                stdRepo.findByEmail(user.getUsername());

	        location.setStudent(std);
	        location.setOrganization(std.getOrg());
	        location.setBus(bus);
	    }

	    else if ("staff".equals(user.getRole())) {

	        Staffdetail staff =
	                staffRepo.findByEmail(user.getUsername());

	        location.setStaff(staff);
	        location.setOrganization(staff.getOrg());
	        location.setBus(bus);
	    }

	    else {
	        return ResponseEntity.badRequest()
	                .body("Invalid Role");
	    }

	    Location savedLocation =
	            locationRepo.save(location);

	    Map<String, Object> result = new HashMap<>();
	    result.put("status", "success");
	    result.put("data", savedLocation);

	    return ResponseEntity.ok(result);
	}
	
	
	
	@GetMapping("/getLocation")
	public ResponseEntity<?> getLocation(
	        @AuthenticationPrincipal AuthUser user) {

	    Integer orgId = null;

	    if ("student".equals(user.getRole())) {

	        StudentForm std =
	                stdRepo.findByEmail(user.getUsername());

	        orgId = std.getOrg().getId();
	    }

	    else if ("staff".equals(user.getRole())) {

	        Staffdetail staff =
	                staffRepo.findByEmail(user.getUsername());

	        orgId = staff.getOrg().getId();
	    }

	    else if("organization".equals(user.getRole())) {
	    	orgId =user.getId();
	    }
	    else {
	        return ResponseEntity.badRequest()
	                .body("Invalid Role");
	    }
	    
	    

	    List<Location> locations =
	            locationRepo.findByOrganization_Id(orgId);

	    Map<String,Object> result = new HashMap<>();
	    result.put("status", "success");
	    result.put("data", locations);

	    return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getBus")
	public ResponseEntity<?> getBus(
	        @AuthenticationPrincipal AuthUser user) {

	    Integer orgId = null;

	    if ("student".equals(user.getRole())) {

	        StudentForm std =
	                stdRepo.findByEmail(user.getUsername());

	        orgId = std.getOrg().getId();
	    }

	    else if ("staff".equals(user.getRole())) {

	        Staffdetail staff =
	                staffRepo.findByEmail(user.getUsername());

	        orgId = staff.getOrg().getId();
	    }
	    else if("organization".equals(user.getRole())) {
	    	orgId =user.getId();
	    }

	    else {
	        return ResponseEntity.badRequest()
	                .body("Invalid Role");
	    }

	    List<Bus> buses =
	            busRepo.findByOrg_Id(orgId);

	    Map<String,Object> result = new HashMap<>();
	    result.put("status", "success");
	    result.put("data", buses);

	    return ResponseEntity.ok(result);
	}
	
	
}
