/**
 * 
 */
package com.example.apparel.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.apparel.model.Item;

/**
 * @author rajarshi
	private String id;
	private Integer price;
	private Integer stock;
	private String gender;
	private String category;
	private Date date;
	private Integer discount;
	private Boolean hasDiscount;
	private String name;
	private String description;

 *
 */
@Repository
public interface ItemRepository extends MongoRepository<Item,String> {
	
	List<Item> findByGender(String gender);
	Optional<Item> findById(String id);
	List<Item> findByHasDiscount(Boolean hasDiscount);
	List<Item> findByCategory(String category);
	List<Item> findByName(String name);
}
