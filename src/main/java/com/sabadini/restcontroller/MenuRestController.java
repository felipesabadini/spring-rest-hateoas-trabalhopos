package com.sabadini.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sabadini.hateoas.Hateoas;
import com.sabadini.hateoas.ItemHateoas;

@RestController
@RequestMapping(path = "/api")
public class MenuRestController {

	@RequestMapping(method = RequestMethod.GET, path = "/menus")
	public ResponseEntity<Hateoas> getMenu() {	
		final Hateoas hateoas = new Hateoas();
		hateoas.addItemHateoas(new ItemHateoas("customer")
								.addLink("create", "api/customers/create")
								.addLink("list", "api/customers/list"));

		hateoas.addItemHateoas(new ItemHateoas("product")
								.addLink("create", "api/products/create")
								.addLink("list", "api/products/list"));		
		
		hateoas.addItemHateoas(new ItemHateoas("order")
								.addLink("create", "api/orders/create")
								.addLink("list", "api/orders/list"));	
		
		return ResponseEntity.ok(hateoas);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/menu-customers")
	public ResponseEntity<Hateoas> getCustomer() {		
		final Hateoas hateoas = new Hateoas();
		hateoas.addItemHateoas(new ItemHateoas("customer")
								.addLink("home", "/")
								.addLink("create", "api/customers/creates")
								.addLink("list", "api/customers/list"));		
		
		return ResponseEntity.ok(hateoas);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/menu-products")
	public ResponseEntity<Hateoas> getProduct() {		
		final Hateoas hateoas = new Hateoas();
		hateoas.addItemHateoas(new ItemHateoas("product")
								.addLink("home", "/")
								.addLink("create", "api/products/create")
								.addLink("list", "api/products/list"));		
		
		return ResponseEntity.ok(hateoas);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/menu-orders")
	public ResponseEntity<Hateoas> getOrder() {		
		final Hateoas hateoas = new Hateoas();
		hateoas.addItemHateoas(new ItemHateoas("order")
								.addLink("home", "/")
								.addLink("create", "api/orders/create")
								.addLink("list", "api/orders/list"));		
		
		return ResponseEntity.ok(hateoas);
	}		
}
