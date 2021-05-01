package com.example.apparel.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="listSearchHistory" )
public class ListSearchHistory {
	@Id
	private String id;
	private String userId;
	List<SearchHistory> history;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<SearchHistory> getHistory() {
		return history;
	}
	public void setHistory(List<SearchHistory> history) {
		this.history = history;
	}
	public ListSearchHistory(String userId) {
		super();
		this.userId = userId;
		this.history = new ArrayList<>();
		List<String> items = Arrays.asList("Shoe","Top","Skirt","Pant","Shirt","Tshirt");
		for(int i=0;i<items.size();i++) {
			this.history.add(new SearchHistory(items.get(i),0));
		}
	}

}
