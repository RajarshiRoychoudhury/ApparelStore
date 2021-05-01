package com.example.apparel.model;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="item")
public class Item {
	@Id
	private String id;
	private Integer price;
	private Integer stock;
	private String gender;
	private String category;
	private Date date;
	private Integer discount;
	private Boolean hasDiscount;
	private String name;

	public Boolean getHasDiscount() {
		return hasDiscount;
	}
	public void setHasDiscount(Boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Item(Integer price, Integer stock, String gender, String category, Integer discount,
			Boolean hasDiscount, String name) {
		super();
		this.price = price;
		this.stock = stock;
		this.gender = gender;
		this.category = category;
		this.discount = discount;
		this.hasDiscount = hasDiscount;
		this.name = name;
		this.date = new Date();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", price=" + price + ", stock=" + stock + ", gender=" + gender + ", category="
				+ category + ", date=" + date + ", discount=" + discount + ", hasDiscount=" + hasDiscount + "]";
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	
	
	
}
