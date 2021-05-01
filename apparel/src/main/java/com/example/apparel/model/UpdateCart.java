package com.example.apparel.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.apparel.repository.ItemRepository;

public class UpdateCart {
	
	@Autowired
	ItemRepository repository;
	
	private String id;
	private Integer quantity;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public UpdateCart(String id, Integer quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "UpdateCart [id=" + id + ", quantity=" + quantity + "]";
	}
	


}
