package com.sabadini.hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class ItemHateoas extends ResourceSupport {

	private final String data;
	
	public ItemHateoas(String data) {
		this.data = data;
	}
	
	public ItemHateoas addLink(String href, String rel) {
		this.add(new Link(href, rel));
		return this;
	}  
	
	public String getData() {
		return this.data;
	}	
	
}
