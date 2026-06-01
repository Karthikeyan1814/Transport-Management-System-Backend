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
	
		private String firstname;
		private String lastname;
		private String dob;
		private String joindate;
		private String dept;
		private int year;
		private long mobileno;
		private String email;
		private String parentname;
		private long parentno;
		private String bording;
		private long pincode;
		private String password;
		private String confirmpassword;
		private String note;
		private String status;
		
		public int getSid() {
			return sid;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
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
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getJoindate() {
			return joindate;
		}
		public void setJoindate(String joindate) {
			this.joindate = joindate;
		}
		public String getDept() {
			return dept;
		}
		public void setDept(String dept) {
			this.dept = dept;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public long getMobileno() {
			return mobileno;
		}
		public void setMobileno(long mobileno) {
			this.mobileno = mobileno;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getParentname() {
			return parentname;
		}
		public void setParentname(String parentname) {
			this.parentname = parentname;
		}
		public long getParentno() {
			return parentno;
		}
		public void setParentno(long parentno) {
			this.parentno = parentno;
		}
		public String getBording() {
			return bording;
		}
		public void setBording(String bording) {
			this.bording = bording;
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
		public String getConfirmpassword() {
			return confirmpassword;
		}
		public void setConfirmpassword(String confirmpassword) {
			this.confirmpassword = confirmpassword;
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
		}
		public StudentForm(String firstname, String lastname, String dob, String joindate, String dept, int year,
				long mobileno, String email, String parentname, long parentno, String bording, long pincode,
				String password, String confirmpassword, String note ,String status) {
			super();
			this.firstname = firstname;
			this.lastname = lastname;
			this.dob = dob;
			this.joindate = joindate;
			this.dept = dept;
			this.year = year;
			this.mobileno = mobileno;
			this.email = email;
			this.parentname = parentname;
			this.parentno = parentno;
			this.bording = bording;
			this.pincode = pincode;
			this.password = password;
			this.confirmpassword = confirmpassword;
			this.note = note;
			this.status=status;
		}
		public StudentForm() {
			
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
}
