package com.example.apparel.model;

public class HomePage {
	Item[] discounted;
	Item[] newArrivals;
	Item[] based;
	public Item[] getDiscounted() {
		return discounted;
	}
	public void setDiscounted(Item[] discounted) {
		this.discounted = discounted;
	}
	public Item[] getNewArrivals() {
		return newArrivals;
	}
	public void setNewArrivals(Item[] newArrivals) {
		this.newArrivals = newArrivals;
	}
	public Item[] getBased() {
		return based;
	}
	public void setBased(Item[] based) {
		this.based = based;
	}
	public HomePage(Item[] discounted, Item[] newArrivals, Item[] based) {
		super();
		this.discounted = discounted;
		this.newArrivals = newArrivals;
		this.based = based;
	}
	
	
}
