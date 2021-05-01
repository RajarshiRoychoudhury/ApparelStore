package com.example.apparel.model;

public class SearchHistory {
	
	private String category;
	private Integer times;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	@Override
	public String toString() {
		return "SearchHistory [category=" + category + ", times=" + times + "]";
	}
	public SearchHistory(String category, Integer times) {
		super();
		this.category = category;
		this.times = times;
	}
	
}
