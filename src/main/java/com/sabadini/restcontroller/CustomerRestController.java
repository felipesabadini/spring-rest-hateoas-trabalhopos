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

import com.sabadini.domain.Customer;
import com.sabadini.hateoas.Hateoas;
import com.sabadini.hateoas.ItemHateoas;
import com.sabadini.repository.CustomerRepository;

@RestController
@RequestMapping(path = "/api/customers")
public class CustomerRestController {

	private final CustomerRepository repository;

	@Autowired
	public CustomerRestController(CustomerRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Hateoas> save(@RequestBody Customer customer) {		
		customer.generateID();
		final Customer result = this.repository.saveAndFlush(customer);
		final Hateoas hateoas = new Hateoas(result);		
		hateoas.addItemHateoas(new ItemHateoas("customer")
								.addLink("home", "/")
								.addLink("create", "api/customers/create")
								.addLink("details", "api/customers/details/" + result.getId())
								.addLink("edit", "api/customers/edit/" + result.getId())
								.addLink("remove", "api/customers/remove/" + result.getId())								
								.addLink("list", "api/customers/list"));
		
		return ResponseEntity.ok(hateoas);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<Hateoas> getByID(@PathVariable String id) {
		final Customer result = this.repository.findOne(id);
		final Hateoas hateoas = new Hateoas(result);		
		hateoas.addItemHateoas(new ItemHateoas("customer")
								.addLink("home", "/")
								.addLink("create", "api/customers/create")
								.addLink("edit", "api/customers/edit/" + result.getId())
								.addLink("remove", "api/customers/remove/" + result.getId())
								.addLink("list", "api/customers/list"));
		
		return ResponseEntity.ok(hateoas);		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<Hateoas> update(@PathVariable String id, @RequestBody Customer customer) {
		final Customer result = this.repository.saveAndFlush(customer);
		final Hateoas hateoas = new Hateoas(result);		
		hateoas.addItemHateoas(new ItemHateoas("customer")
								.addLink("home", "/")
								.addLink("create", "api/customers/create")
								.addLink("details", "api/customers/details/" + result.getId())
								.addLink("remove", "api/customers/remove/" + result.getId())
								.addLink("list", "api/customers/list"));
		
		return ResponseEntity.ok(hateoas);		
	}	
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<Hateoas> delete(@PathVariable String id) {
		this.repository.delete(id);
		final Hateoas hateoas = new Hateoas();		
		hateoas.addItemHateoas(new ItemHateoas("customer")
								.addLink("home", "/")
								.addLink("create", "api/customers/create")
								.addLink("list", "api/customers/list"));
		
		return ResponseEntity.ok(hateoas);		
	}	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Hateoas>> getAll() {
		final List<Hateoas> listHateoas = new ArrayList<>();
		this.repository.findAll().forEach(c -> {
			final Hateoas hateoas = new Hateoas(c);
			hateoas.addItemHateoas(new ItemHateoas("customer")
					.addLink("details", "api/customers/details/" + c.getId())
					.addLink("edit", "api/customers/edit/" + c.getId())
					.addLink("remove", "api/customers/remove/" + c.getId())
					);
			listHateoas.add(hateoas);
		});				
		
		return ResponseEntity.ok(listHateoas);		
	}	
}
