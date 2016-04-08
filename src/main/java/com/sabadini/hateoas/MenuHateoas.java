package com.sabadini.hateoas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuHateoas {
	public enum Menu {
		MENU,
		MENUCUSTOMER,
		MENUPRODUCT,
		MENUORDER;
	}
	
	private final List<ItemMenuHateoas> data;
	
	public MenuHateoas(Menu menu) {
		this.data = new ArrayList<>();
		this.load(menu);
	}

	private void load(Menu menu) {
		String baseURI = "";
		ItemMenuHateoas customer;
		ItemMenuHateoas product;
		ItemMenuHateoas order;
		switch (menu) {
		case MENU:
			baseURI = "customers"; 
			customer = new ItemMenuHateoas("customer");		
			customer.addLink("create", "/api/" + baseURI + "/create");
			customer.addLink("list", "/api/" + baseURI + "/list");
			this.data.add(customer);

			baseURI = "products";
			product = new ItemMenuHateoas("product");
			product.addLink("create", "/api/" + baseURI + "/create");
			product.addLink("list", "/api/" + baseURI + "/list");
			this.data.add(product);		
			
			baseURI = "orders";
			order = new ItemMenuHateoas("order");
			order.addLink("create", "/api/" + baseURI + "/create");
			order.addLink("list", "/api/" + baseURI + "/list");
			this.data.add(order);			
			break;
			
		case MENUCUSTOMER:
			baseURI = "customers"; 
			customer = new ItemMenuHateoas("customer");
			customer.addLink("home", "/");
			customer.addLink("create", "/api/" + baseURI + "/create");
			customer.addLink("list", "/api/" + baseURI + "/list");
			this.data.add(customer);			
			break;
			
		case MENUPRODUCT:
			
			break;
			
		case MENUORDER:
			
			break;			
		}				
	}
	
	public List<ItemMenuHateoas> getData() {
		return Collections.unmodifiableList(this.data);
	}
}
