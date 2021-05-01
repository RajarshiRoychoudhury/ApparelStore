package com.example.apparel.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.apparel.repository.ItemRepository;

public class CartItemFull {
	private Item item;
	private Integer quantity;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartItemFull [item=" + item + ", quantity=" + quantity + "]";
	}
	public CartItemFull(Item item, Integer quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}

}
