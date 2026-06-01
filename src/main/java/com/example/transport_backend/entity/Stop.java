package com.example.transport_backend.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String time;

    @ManyToOne
    @JoinColumn(name="route_id")
    @JsonBackReference
    private BusRoute route;

    public Stop(){}

    public Long getId(){ return id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public String getTime(){ return time; }
    public void setTime(String time){ this.time = time; }

    public BusRoute getRoute(){ return route; }
    public void setRoute(BusRoute route){ this.route = route; }
}