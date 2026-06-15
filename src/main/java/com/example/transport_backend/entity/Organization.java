package com.example.transport_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String name;
	private String type;
	private String founderName;
	private String university;
	private String address;
	private String pincode;
	private String phone;
	private String email;
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy = "org",cascade = CascadeType.ALL)
	@JsonManagedReference("org")
	private List<StudentForm> std;
	
	@OneToMany(mappedBy="org" ,cascade = CascadeType.ALL)
	@JsonManagedReference("org-staff")
	private List<Staffdetail> staff;
	
	@OneToMany(mappedBy="org" ,cascade = CascadeType.ALL)
	@JsonManagedReference("org-bus")
	private List<Bus> bus;
	
	public List<Staffdetail> getStaff() {
		return staff;
	}

	public List<Bus> getBus() {
		return bus;
	}

	public void setBus(List<Bus> bus) {
		this.bus = bus;
	}

	public void setStaff(List<Staffdetail> staff) {
		this.staff = staff;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFounderName() {
		return founderName;
	}

	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<StudentForm> getStd() {
		return std;
	}

	public void setStd(List<StudentForm> std) {
		this.std = std;
	}

	public Integer getId() {
		return id;
	}

	
	public Organization(String name, String type, String founderName, String university, String address, String pincode,
			String phone, String email, String password, List<StudentForm> std, List<Staffdetail> staff, List<Bus> bus) {
		super();
		this.name = name;
		this.type = type;
		this.founderName = founderName;
		this.university = university;
		this.address = address;
		this.pincode = pincode;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.std = std;
		this.staff = staff;
		this.bus = bus;
	}

	public Organization() {
		
	}

}
