package com.example.transport_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Staffdetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String domain;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String empid;
	private String role;
	private String dept;
	private String transportrole;
	@JsonIgnore
	private String password;
	private String status;

	@OneToMany(mappedBy = "staff")
	@JsonManagedReference("staff-location")
	private List<Location> location;
	
	
	@ManyToOne
	@JoinColumn(name="org_id")
	@JsonBackReference("org-staff")
	private Organization org;
	
	public Integer getId() {
		return id;
	}


	public List<Location> getLocation() {
		return location;
	}


	public void setLocation(List<Location> location) {
		this.location = location;
	}


	public Organization getOrg() {
		return org;
	}



	public void setOrg(Organization org) {
		this.org = org;
	}



	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String  getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmpid() {
		return empid;
	}


	public void setEmpid(String empid) {
		this.empid = empid;
	}




	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}


	public String getTransportrole() {
		return transportrole;
	}


	public void setTransportrole(String transportrole) {
		this.transportrole = transportrole;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	

	public Staffdetail(Integer id, String domain, String firstname, String lastname, String email, String phone,
			String empid,String role, String dept, String transportrole, String password,
			String status) {
		super();
		this.id = id;
		this.domain = domain;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.empid = empid;
	
		this.role = role;
		this.dept = dept;
		this.transportrole = transportrole;
		this.password = password;
		this.status = status;
	}



	public Staffdetail() {
		
	}
}
