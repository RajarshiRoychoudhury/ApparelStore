/**
 * 
 */
package com.example.apparel.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.apparel.model.Cart;

/**
 * @author rajarshi
 *
 */
public interface CartRepository extends MongoRepository<Cart, String> {
	List<Cart> findByUserId(String userId); 
}
