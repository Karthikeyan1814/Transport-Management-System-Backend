package com.example.transport_backend.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	private long busId;
	
	private String busNumber;
	
	private String busDriver;
	
	private long driverNumber;
	
	@ManyToOne
	@JoinColumn(name ="route_id")
	private BusRoute route;
	
	@OneToOne
	@JoinColumn(name="incharge_id")
	private BusIncharge busincharge;
	
	@OneToMany(mappedBy = "bus",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<StudentForm> studentdetails;
	
	@OneToMany(mappedBy="bus",cascade=CascadeType.ALL)
	@JsonManagedReference("location")
	private List<Location> location;

	
	@ManyToOne
	@JoinColumn(name="orgid")
	@JsonIgnore
	private Organization org;
	
	public long getBusId() {
		return busId;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public String getBusDriver() {
		return busDriver;
	}

	public void setBusDriver(String busDriver) {
		this.busDriver = busDriver;
	}

	public long getDriverNumber() {
		return driverNumber;
	}

	
	public List<Location> getLocation() {
		return location;
	}

	public void setLocation(List<Location> location) {
		this.location = location;
	}

	public void setBusId(long busId) {
		this.busId = busId;
	}

	public void setDriverNumber(long driverNumber) {
		this.driverNumber = driverNumber;
	}

	public BusRoute getRoute() {
		return route;
	}

	public void setRoute(BusRoute route) {
		this.route = route;
	}

	public BusIncharge getBusincharge() {
		return busincharge;
	}

	public void setBusincharge(BusIncharge busincharge) {
		this.busincharge = busincharge;
	}

	public List<StudentForm> getStudentdetails() {
		return studentdetails;
	}

	public void setStudentdetails(List<StudentForm> studentdetails) {
		this.studentdetails = studentdetails;
	}

	
	
	public Bus(String busNumber, String busDriver, long driverNumber, BusRoute route, BusIncharge busincharge,
			List<StudentForm> studentdetails, List<Location> location, Organization org) {
		super();
		this.busNumber = busNumber;
		this.busDriver = busDriver;
		this.driverNumber = driverNumber;
		this.route = route;
		this.busincharge = busincharge;
		this.studentdetails = studentdetails;
		this.location = location;
		this.org = org;
	}

	public Bus() {}
	
	
}
