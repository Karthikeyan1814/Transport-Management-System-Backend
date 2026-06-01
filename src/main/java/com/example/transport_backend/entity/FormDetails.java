package com.example.transport_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FormDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String busnumber;
	private String modelname;
	private String numberplate;
	private String usage;
	private String status;
	private String image;
	private String document;
	public FormDetails(Integer id, String busnumber, String modelname, String numberplate, String usage, String status,
			String image, String document) {
		super();
		this.id = id;
		this.busnumber = busnumber;
		this.modelname = modelname;
		this.numberplate = numberplate;
		this.usage = usage;
		this.status = status;
		this.image = image;
		this.document = document;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBusnumber() {
		return busnumber;
	}
	public void setBusnumber(String busnumber) {
		this.busnumber = busnumber;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	public String getNumberplate() {
		return numberplate;
	}
	public void setNumberplate(String numberplate) {
		this.numberplate = numberplate;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public FormDetails() {
		
	}
	
}