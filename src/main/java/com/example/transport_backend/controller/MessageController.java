package com.example.transport_backend.controller;

import java.time.LocalDateTime;
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

import com.example.transport_backend.Repo.BusInchargeRepo;
import com.example.transport_backend.Repo.ImportanMsgRepo;
import com.example.transport_backend.entity.BusIncharge;
import com.example.transport_backend.entity.ImportantMessage;

@RestController
@RequestMapping("/ManageMessage")
public class MessageController {

	@Autowired
	private ImportanMsgRepo msgRepo;
	
	@Autowired
	private BusInchargeRepo inchargeRepo;

	@PostMapping("/sendImpMessage")
	public ResponseEntity<Object> saveMessage(@RequestBody ImportantMessage message){
		BusIncharge incharge=inchargeRepo.findById(message.getSenderid()).orElseThrow();
		message.setSender(incharge);
		message.setCreatedAt(LocalDateTime.now());
		ImportantMessage result =msgRepo.save(message);
		Map<String,Object> response=new HashMap<String,Object>();
		response.put("status", "success");
		response.put("data", result);
		return ResponseEntity.ok(response);
	}
	
	
	
	@GetMapping("/getImpMessage")
	public ResponseEntity<Object> getMessage(){
		List<ImportantMessage> result=msgRepo.findAll();
		Map<String,Object> response=new HashMap<String,Object>();
		response.put("status", "success");
		response.put("data", result);
		return ResponseEntity.ok(response);
	}
}
