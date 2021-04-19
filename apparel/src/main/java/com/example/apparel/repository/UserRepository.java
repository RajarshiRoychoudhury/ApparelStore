/**
 * 
 */
package com.example.apparel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.example.apparel.model.User;

import java.util.*;

/**
 * @author rajarshi
 *
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	  Optional<User> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);

}
