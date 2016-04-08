package com.sabadini.hateoas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Hateoas {

	private final List<ItemHateoas> data;
	private Object entity;
	
	public Hateoas() {
		this.data = new ArrayList<>();
	}
	
	public Hateoas(Object entity) {
		this.data = new ArrayList<>();
		this.entity = entity;
	}

	public Object getEntity() {
		return this.entity;
	}

	public List<ItemHateoas> getData() {
		return Collections.unmodifiableList(this.data);
	}

	public void addItemHateoas(ItemHateoas itemHateoas) {
		this.data.add(itemHateoas);
	}
}
