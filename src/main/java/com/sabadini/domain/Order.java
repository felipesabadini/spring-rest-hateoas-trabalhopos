package com.sabadini.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 3399083495192463314L;

	@Id
	private String id;
	private Long orderNumber;
	@ManyToOne
	private Customer customer;	
	@ManyToOne
	private Product product;	
	private BigDecimal quantity;

	public Order() {}
	
	public void generateID() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return this.id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}	
}
