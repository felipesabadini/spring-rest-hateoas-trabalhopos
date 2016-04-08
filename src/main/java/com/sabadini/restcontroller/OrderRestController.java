package com.sabadini.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sabadini.domain.Order;
import com.sabadini.hateoas.Hateoas;
import com.sabadini.hateoas.ItemHateoas;
import com.sabadini.repository.OrderRepository;

@RestController
@RequestMapping(path = "/api/orders")
public class OrderRestController {

	private final OrderRepository repository;

	@Autowired
	public OrderRestController(OrderRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Hateoas> save(@RequestBody Order order) {		
		order.generateID();
		final Order result = this.repository.saveAndFlush(order);
		final Hateoas hateoas = new Hateoas(result);		
		hateoas.addItemHateoas(new ItemHateoas("order")
								.addLink("home", "/")
								.addLink("create", "api/orders/create")
								.addLink("details", "api/orders/details/" + result.getId())
								.addLink("edit", "api/orders/edit/" + result.getId())
								.addLink("remove", "api/orders/remove/" + result.getId())								
								.addLink("list", "api/orders/list"));
		
		return ResponseEntity.ok(hateoas);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<Hateoas> getByID(@PathVariable String id) {
		final Order result = this.repository.findOne(id);
		final Hateoas hateoas = new Hateoas(result);		
		hateoas.addItemHateoas(new ItemHateoas("order")
								.addLink("home", "/")
								.addLink("create", "api/orders/create")
								.addLink("edit", "api/orders/edit/" + result.getId())
								.addLink("remove", "api/orders/remove/" + result.getId())
								.addLink("list", "api/orders/list"));
		
		return ResponseEntity.ok(hateoas);		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<Hateoas> update(@PathVariable String id, @RequestBody Order order) {
		final Order result = this.repository.saveAndFlush(order);
		final Hateoas hateoas = new Hateoas(result);		
		hateoas.addItemHateoas(new ItemHateoas("order")
								.addLink("home", "/")
								.addLink("create", "api/orders/create")
								.addLink("details", "api/orders/details/" + result.getId())
								.addLink("remove", "api/orders/remove/" + result.getId())
								.addLink("list", "api/orders/list"));
		
		return ResponseEntity.ok(hateoas);		
	}	
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<Hateoas> delete(@PathVariable String id) {
		this.repository.delete(id);
		final Hateoas hateoas = new Hateoas();		
		hateoas.addItemHateoas(new ItemHateoas("order")
								.addLink("home", "/")
								.addLink("create", "api/orders/create")
								.addLink("list", "api/orders/list"));
		
		return ResponseEntity.ok(hateoas);		
	}	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Hateoas>> getAll() {
		final List<Hateoas> listHateoas = new ArrayList<>();
		this.repository.findAll().forEach(c -> {
			final Hateoas hateoas = new Hateoas(c);
			hateoas.addItemHateoas(new ItemHateoas("order")
					.addLink("details", "api/orders/details/" + c.getId())
					.addLink("edit", "api/orders/edit/" + c.getId())
					.addLink("remove", "api/orders/remove/" + c.getId())
					);
			listHateoas.add(hateoas);
		});				
		
		return ResponseEntity.ok(listHateoas);		
	}	
}
