package com.example.transport_backend.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transport_backend.Repo.BusRepo;
import com.example.transport_backend.Repo.BusRouteRepo;
import com.example.transport_backend.Repo.LocationRepo;
import com.example.transport_backend.entity.Bus;
import com.example.transport_backend.entity.BusRoute;
import com.example.transport_backend.entity.Location;
import com.example.transport_backend.entity.Stop;

@RestController
@RequestMapping("/ManageBus")
public class BusController {

	@Autowired
	private BusRouteRepo routeRepo;

	@Autowired
	private BusRepo busRepo;
	
	@Autowired
	private LocationRepo locationRepo;
	
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
	
	//saveLocation
	@PostMapping("/RequestLocation")
	public ResponseEntity<Object> saveLocation(@RequestBody Location location){
		
		Bus bus=busRepo.findById(location.getBusid()).orElseThrow();
		location.setBus(bus);
		Location response=locationRepo.save(location);
		 Map<String,Object> result = new HashMap<>();

		    result.put("status", "success");
		    result.put("data", response);

		    return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getBus")
	public List<Bus> getBus(){
		return busRepo.findAll();
	}
	
}
