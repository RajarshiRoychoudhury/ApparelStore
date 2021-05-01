package com.example.apparel.model;

import java.util.*;

public class PQueue {
	 private PriorityQueue queue;
	 private SearchHistoryComparator comparator;
	 public PQueue(List<SearchHistory> history){
		 comparator = new SearchHistoryComparator();
		 queue = new PriorityQueue<>(10,comparator);
		 for(int i=0;i<history.size();i++) {
			 queue.add(history.get(i));
		 }
		 
	 }
	 public List<SearchHistory> getTopThree(){
		 SearchHistory top1 = (SearchHistory) queue.remove();
		 SearchHistory top2 = (SearchHistory) queue.remove();
		 SearchHistory top3 = (SearchHistory) queue.remove();
		 return Arrays.asList(top1,top2,top3);
	 }
}
