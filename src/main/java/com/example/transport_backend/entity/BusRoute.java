package com.example.transport_backend.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNo;

    @OneToMany(mappedBy="route", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Stop> stops;

    public BusRoute(){}

    public Long getId(){ return id; }

    public String getBusNo(){ return busNo; }
    public void setBusNo(String busNo){ this.busNo = busNo; }

    public List<Stop> getStops(){ return stops; }
    public void setStops(List<Stop> stops){ this.stops = stops; }
}