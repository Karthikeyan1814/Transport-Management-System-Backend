package com.example.transport_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	private String link;
	
	@Transient
	private Integer busid;
	
	public Integer getBusid() {
		return busid;
	}
	
	@ManyToOne
	@JoinColumn(name="student_id")
	@JsonBackReference("std-location")
	private StudentForm student;
	
	
	@ManyToOne
	@JoinColumn(name="staff_id")
	@JsonBackReference("staff-location")
	private Staffdetail staff;




	public Staffdetail getStaff() {
		return staff;
	}

	public void setStaff(Staffdetail staff) {
		this.staff = staff;
	}

	public StudentForm getStudent() {
		return student;
	}

	public void setStudent(StudentForm std) {
		this.student = std;
	}

	public void setBusid(Integer busid) {
		this.busid = busid;
	}

	@ManyToOne
	@JoinColumn(name="bus_id")
	@JsonBackReference("location")
	private Bus bus;
	
	private String status="waiting";

	@ManyToOne
	@JoinColumn(name="org_id")
	@JsonBackReference("org-location")
	private Organization organization;
	
	
	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@JsonProperty("Organization")
	public String getOrganizationName() {
		return organization!=null?organization.getName():null;
	}

	@JsonProperty("busRegNumber")
	public Long getBusRegNumber() {
		return bus!=null?bus.getBusId():null;
	}
	
	@JsonProperty("busNumber")
	public String getBusNumber() {
		return bus!=null?bus.getBusNumber():null;
	} 
	
	public int getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public Location() {}
	
}
