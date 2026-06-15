package com.example.transport_backend.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;
    private String startPoint;
    private String dropPoint;

    @OneToMany(mappedBy = "route",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Bus> buses;
    
    @OneToMany(mappedBy="route", cascade=CascadeType.ALL)
    @JsonManagedReference("stops")
    private List<Stop> stops;

    public BusRoute(){}

    public Long getId(){ return id; }

    

    public List<Stop> getStops(){ return stops; }
    public void setStops(List<Stop> stops){ this.stops = stops; }

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getDropPoint() {
		return dropPoint;
	}

	public void setDropPoint(String dropPoint) {
		this.dropPoint = dropPoint;
	}

	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}

	public BusRoute(String routeName, String startPoint, String dropPoint, List<Bus> buses, List<Stop> stops) {
		super();
		this.routeName = routeName;
		this.startPoint = startPoint;
		this.dropPoint = dropPoint;
		this.buses = buses;
		this.stops = stops;
	}
    
    
    
}