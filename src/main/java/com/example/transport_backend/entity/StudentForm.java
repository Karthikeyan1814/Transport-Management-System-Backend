package com.example.transport_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentForm {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private Integer sid;
	
//		 const [signup,setSignUp]=useState({
//	            domain:"student",
//	            firstname:"",
//	            lastname:"",
//	            email:"",
//	            phone:0,
//	            dob:"",
//	            date:"",
//	            dept:"",
//	            currentyear:0,
//	            parentname:"",
//	            parentmobile:0,
//	            address:"",
//	            pincode:0,
//	            password:"",
//	            bordingpoint:"",
//	            organization:""
//	        })
		private String domain;
		private String firstname;
		private String lastname;
		private String email;
		private String dob;
		private long phone;
		private String date;
		private String dept;
		private int currentyear;
		private String parentname;
		private long parentmobile;
		private String address;
		private long pincode;
		private String password;
		private String bordingpoint;
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
		public long getPhone() {
			return phone;
		}
		public void setPhone(long phone) {
			this.phone = phone;
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
		public long getParentmobile() {
			return parentmobile;
		}
		public void setParentmobile(long parentmobile) {
			this.parentmobile = parentmobile;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public long getPincode() {
			return pincode;
		}
		public void setPincode(long pincode) {
			this.pincode = pincode;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getBordingpoint() {
			return bordingpoint;
		}
		public void setBordingpoint(String bordingpoint) {
			this.bordingpoint = bordingpoint;
		}
		public String getOrganization() {
			return organization;
		}
		public void setOrganization(String organization) {
			this.organization = organization;
		}

		public StudentForm(String domain, String firstname, String lastname, String email, String dob, long phone,
				String date, String dept, int currentyear, String parentname, long parentmobile, String address,
				long pincode, String password, String bordingpoint, String organization, String status) {
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
			this.organization = organization;
			this.status = status;
		}
		
		public StudentForm() {
			
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
