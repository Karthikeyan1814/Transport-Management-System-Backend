package com.example.transport_backend.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class StudentForm {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer sid;
	
		private String domain;
		private String firstname;
		private String lastname;
		private String email;
		private String dob;
		private String phone;
		private String date;
		private String dept;
		private int currentyear;
		private String parentname;
		private String parentmobile;
		private String address;
		private String pincode;
		@JsonIgnore
		private String password;
		private String bordingpoint;
		
		@Transient
		private int orgId;
		@Transient
		private int busId;
		
		@ManyToOne
		@JoinColumn(name= "org_id")
		@JsonBackReference("org")
		private Organization org;
		

		 @ManyToOne
		 @JoinColumn(name="bus_id")
		 @JsonBackReference("student")
		 private Bus bus;
		 
		public Bus getBus() {
			return bus;
		}

		
		 public int getOrgId() {
			return orgId;
		}


		public void setOrgId(int orgId) {
			this.orgId = orgId;
		}


		public int getBusId() {
			return busId;
		}


		public void setBusId(int busId) {
			this.busId = busId;
		}


		 public Organization getOrg() {
				return org;
			}

			public void setOrg(Organization org) {
				this.org = org;
			}
			
		 public void setBus(Bus bus) {
			 this.bus = bus;
		 }

		private String organization;
		private String status;
		

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Integer getSid() {
			return sid;
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
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getDept() {
			return dept;
		}
		public void setDept(String dept) {
			this.dept = dept;
		}
		public int getCurrentyear() {
			return currentyear;
		}
		public void setCurrentyear(int currentyear) {
			this.currentyear = currentyear;
		}
		public String getParentname() {
			return parentname;
		}
		public void setParentname(String parentname) {
			this.parentname = parentname;
		}
				public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	
		public String getOrganization() {
			return organization;
		}
		public void setOrganization(String organization) {
			this.organization = organization;
		}

		
		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getParentmobile() {
			return parentmobile;
		}

		public void setParentmobile(String parentmobile) {
			this.parentmobile = parentmobile;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getBordingpoint() {
			return bordingpoint;
		}

		public void setBordingpoint(String bordingpoint) {
			this.bordingpoint = bordingpoint;
		}

		public StudentForm() {
			
		}

		public StudentForm(String domain, String firstname, String lastname, String email, String dob, String phone,
				String date, String dept, int currentyear, String parentname, String parentmobile, String address,
				String pincode, String password, String bordingpoint, Organization org, Bus bus, String organization,
				String status) {
			super();
			this.domain = domain;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.dob = dob;
			this.phone = phone;
			this.date = date;
			this.dept = dept;
			this.currentyear = currentyear;
			this.parentname = parentname;
			this.parentmobile = parentmobile;
			this.address = address;
			this.pincode = pincode;
			this.password = password;
			this.bordingpoint = bordingpoint;
			this.org = org;
			this.bus = bus;
			this.organization = organization;
			this.status = status;
		}
		
		
		}
		
		
//	 firstname:"",
//     lastname:"",
//     dob:"",
//     joindate:"",
//     dept:null,
//     year:0,
//     mobileno:0,
//     email:"",
//     parentname:"",
//     parentno:0,
//     bording:"",
//     pincode:0,
//     password:"",
//     confirmpassword:"",
//     note:"unchecked"
