package com.example.transport_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
@Entity
public class ImportantMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String heading;
	private String context;
	private String addtional_info;
	private LocalDateTime createdAt;
	
	@Transient
	private Integer senderid;
	
	@ManyToOne
	@JoinColumn(name="sender_id")
	private BusIncharge sender;
	
	private String recevier;
	
	
	public Integer getSenderid() {
		return senderid;
	}

	public void setSenderid(Integer senderid) {
		this.senderid = senderid;
	}

	

	public int getId() {
		return id;
	}
	
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getAddtioonal_info() {
		return addtional_info;
	}
	public void setAddtioonal_info(String addtioonal_info) {
		this.addtional_info = addtioonal_info;
	}

	public ImportantMessage() {
		
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public BusIncharge getSender() {
		return sender;
	}

	public void setSender(BusIncharge sender) {
		this.sender = sender;
	}

	public String getRecevier() {
		return recevier;
	}

	public void setRecevier(String recevier) {
		this.recevier = recevier;
	}

	public ImportantMessage(String heading, String context, String addtional_info, LocalDateTime createdAt,
			Integer senderid, BusIncharge sender, String recevier) {
		super();
		this.heading = heading;
		this.context = context;
		this.addtional_info = addtional_info;
		this.createdAt = createdAt;
		this.senderid = senderid;
		this.sender = sender;
		this.recevier = recevier;
	}

    

	
	
	
	
}
