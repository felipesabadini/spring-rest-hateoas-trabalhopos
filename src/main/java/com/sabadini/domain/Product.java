package com.sabadini.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 2519659108323910676L;

	@Id
	private String id;
	private String name;
	
	public Product() {}
	
	public void generateID() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
