package com.example.transport_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Staffdetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String domain;
	private String firstname;
	private String lastname;
	private String email;
	private long phone;
	private String empid;
	private String orgid;
	private String role;
	private String dept;
	private String transportrole;
	private String password;
	private String status;

	
	public Integer getId() {
		return id;
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


	public long getPhone() {
		return phone;
	}


	public void setPhone(long phone) {
		this.phone = phone;
	}


	public String getEmpid() {
		return empid;
	}


	public void setEmpid(String empid) {
		this.empid = empid;
	}


	public String getOrgid() {
		return orgid;
	}


	public void setOrgid(String orgid) {
		this.orgid = orgid;
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

	

	public Staffdetail(Integer id, String domain, String firstname, String lastname, String email, long phone,
			String empid, String orgid, String role, String dept, String transportrole, String password,
			String status) {
		super();
		this.id = id;
		this.domain = domain;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.empid = empid;
		this.orgid = orgid;
		this.role = role;
		this.dept = dept;
		this.transportrole = transportrole;
		this.password = password;
		this.status = status;
	}



	public Staffdetail() {
		
	}
}
