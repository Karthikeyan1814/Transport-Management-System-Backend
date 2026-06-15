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

	public void setBusid(Integer busid) {
		this.busid = busid;
	}

	@ManyToOne
	@JoinColumn(name="bus_id")
	@JsonBackReference("location")
	private Bus bus;
	
	private String status="waiting";

	
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

	
	
	public Location(String link, Integer busid, Bus bus, String status) {
		super();
		this.link = link;
		this.busid = busid;
		this.bus = bus;
		this.status = status;
	}

	public Location() {}
	
}
