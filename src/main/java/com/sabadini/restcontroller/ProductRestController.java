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

import com.sabadini.domain.Product;
import com.sabadini.hateoas.Hateoas;
import com.sabadini.hateoas.ItemHateoas;
import com.sabadini.repository.ProductRepository;

@RestController
@RequestMapping(path = "/api/products")
public class ProductRestController {

	private final ProductRepository repository;

	@Autowired
	public ProductRestController(ProductRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Hateoas> save(@RequestBody Product product) {		
		product.generateID();
		final Product result = this.repository.saveAndFlush(product);
		final Hateoas hateoas = new Hateoas(result);		
		hateoas.addItemHateoas(new ItemHateoas("product")
								.addLink("home", "/")
								.addLink("create", "api/products/create")
								.addLink("details", "api/products/details/" + result.getId())
								.addLink("edit", "api/products/edit/" + result.getId())
								.addLink("remove", "api/products/remove/" + result.getId())								
								.addLink("list", "api/products/list"));
		
		return ResponseEntity.ok(hateoas);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<Hateoas> getByID(@PathVariable String id) {
		final Product result = this.repository.findOne(id);
		final Hateoas hateoas = new Hateoas(result);		
		hateoas.addItemHateoas(new ItemHateoas("product")
								.addLink("home", "/")
								.addLink("create", "api/products/create")
								.addLink("edit", "api/products/edit/" + result.getId())
								.addLink("remove", "api/products/remove/" + result.getId())
								.addLink("list", "api/products/list"));
		
		return ResponseEntity.ok(hateoas);		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<Hateoas> update(@PathVariable String id, @RequestBody Product product) {
		final Product result = this.repository.saveAndFlush(product);
		final Hateoas hateoas = new Hateoas(result);		
		hateoas.addItemHateoas(new ItemHateoas("product")
								.addLink("home", "/")
								.addLink("create", "api/products/create")
								.addLink("details", "api/products/details/" + result.getId())
								.addLink("remove", "api/products/remove/" + result.getId())
								.addLink("list", "api/products/list"));
		
		return ResponseEntity.ok(hateoas);		
	}	
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<Hateoas> delete(@PathVariable String id) {
		this.repository.delete(id);
		final Hateoas hateoas = new Hateoas();		
		hateoas.addItemHateoas(new ItemHateoas("product")
								.addLink("home", "/")
								.addLink("create", "api/products/create")
								.addLink("list", "api/products/list"));
		
		return ResponseEntity.ok(hateoas);		
	}	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Hateoas>> getAll() {
		final List<Hateoas> listHateoas = new ArrayList<>();
		this.repository.findAll().forEach(c -> {
			final Hateoas hateoas = new Hateoas(c);
			hateoas.addItemHateoas(new ItemHateoas("product")
					.addLink("details", "api/products/details/" + c.getId())
					.addLink("edit", "api/products/edit/" + c.getId())
					.addLink("remove", "api/products/remove/" + c.getId())
					);
			listHateoas.add(hateoas);
		});				
		
		return ResponseEntity.ok(listHateoas);		
	}	
	
}
