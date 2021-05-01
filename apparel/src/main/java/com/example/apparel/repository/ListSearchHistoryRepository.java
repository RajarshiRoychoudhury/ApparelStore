package com.example.apparel.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.apparel.model.ListSearchHistory;

public interface ListSearchHistoryRepository extends MongoRepository<ListSearchHistory, String> {
	List<ListSearchHistory> findByUserId(String userId);
}
