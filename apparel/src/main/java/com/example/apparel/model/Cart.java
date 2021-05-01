package com.example.apparel.model;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="cart")
public class Cart {
	
	@Id
	private String id;
	private String userId;
	private List<CartItem> cartItems ;
	
	public Cart(String userId, List<CartItem> cartItems) {
		super();
		this.userId = userId;
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", cartItems=" + cartItems + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public void addItem(CartItem item) {
		List<CartItem> filtered = this.cartItems.stream().filter(cartItem->cartItem.getId().equals(item.getId())).collect(Collectors.toList());
		if(filtered.size()>0) {
			CartItem existingItem = filtered.get(0);
			existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
		}
		else {
			this.cartItems.add(item);
		}
	}
	public void removeItem(CartItem item) {
		this.cartItems = this.cartItems.stream().filter(cartitem->(!cartitem.getId().equals(item.getId()))).collect(Collectors.toList());
	}
	
	
}
