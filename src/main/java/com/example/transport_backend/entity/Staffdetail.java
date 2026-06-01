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

	private String firstname;
	private String lastname;
	private String email;
	private long mobile;
	private String dept;
	private String role;
	private String empid;
	private String password;
	private String confirmpassword;
	private String transportrole;
	private String status;
//	 firstname:"",
//     lastname:"",
//     email:"",
//     mobile:0,
//     dept:"",
//     role:"",
//     empid:"",
//     password:"",
//     confirmpassword:"",
//     transportrole:""
	
	public int getId() {
		return id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getTransportrole() {
		return transportrole;
	}
	public void setTransportrole(String transportrole) {
		this.transportrole = transportrole;
	}
	public Staffdetail(String firstname, String lastname, String email, long mobile, String dept, String role,
			String empid, String password, String confirmpassword, String transportrole,String status) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.mobile = mobile;
		this.dept = dept;
		this.role = role;
		this.empid = empid;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.transportrole = transportrole;
		this.status=status;
	}
	
	public Staffdetail() {
		
	}
}
