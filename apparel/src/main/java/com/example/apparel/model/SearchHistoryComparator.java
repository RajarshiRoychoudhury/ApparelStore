package com.example.apparel.model;

public class SearchHistoryComparator implements java.util.Comparator<SearchHistory> {

	@Override
	public int compare(SearchHistory o1, SearchHistory o2) {
		return o2.getTimes()-(o1.getTimes());
	}

}
