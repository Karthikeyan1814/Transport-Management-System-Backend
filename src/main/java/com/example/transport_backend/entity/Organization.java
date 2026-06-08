package com.example.transport_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer Orgid;

	private String Name;
	private String Type;
	private String FounderName;
	private String University;
	private String Address;
	private int Pincode;
	private long Phone;
	private String Email;
	private String Password;
	public Integer getOrgid() {
		return Orgid;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getFounderName() {
		return FounderName;
	}
	public void setFounderName(String founderName) {
		FounderName = founderName;
	}
	public String getUniversity() {
		return University;
	}
	public void setUniversity(String university) {
		University = university;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getPincode() {
		return Pincode;
	}
	public void setPincode(int pincode) {
		Pincode = pincode;
	}
	public long getPhone() {
		return Phone;
	}
	public void setPhone(long phone) {
		Phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Organization(Integer orgid, String name, String type, String founderName, String university, String address,
			int pincode, long phone, String email, String password) {
		super();
		Name = name;
		Type = type;
		FounderName = founderName;
		University = university;
		Address = address;
		Pincode = pincode;
		Phone = phone;
		Email = email;
		Password = password;
	}
	
	public Organization() {
		
	}

}
