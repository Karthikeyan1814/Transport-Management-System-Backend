package com.example.transport_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class BusIncharge {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private String name;
	private String role;
	@OneToOne
	@JoinColumn(name = "staff_id")
	private Staffdetail staffdetail;

	@OneToMany(mappedBy = "sender")
	@JsonIgnore
	private List<ImportantMessage> message;
	
	public List<ImportantMessage> getMessage() {
		return message;
	}




	public void setMessage(List<ImportantMessage> message) {
		this.message = message;
	}




	public long getId() {
		return id;
	}

	
	

	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Staffdetail getStaffdetail() {
		return staffdetail;
	}

	public void setStaffdetail(Staffdetail staffdetail) {
		this.staffdetail = staffdetail;
	}

	public BusIncharge() {}




	public BusIncharge(String name, String role, Staffdetail staffdetail) {
		super();
		this.name = name;
		this.role = role;
		this.staffdetail = staffdetail;
	}





}
