package com.sabadini.hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class ItemMenuHateoas extends ResourceSupport {

	private final String data;

	public ItemMenuHateoas(String name) {
		this.data = name;
	}
	
	public ItemMenuHateoas addLink(String href, String rel) {
		this.add(new Link(href, rel));
		return this;
	}  
	
	public String getData() {
		return this.data;
	}
}
