/**
 * 
 */
package com.example.apparel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apparel.model.User;
import com.example.apparel.repository.UserRepository;

import java.util.*;

/**
 * @author rajarshi
 *
 */

@RestController
public class UserController {
	private UserRepository repository;
	
	public UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/userbyemail")
	public User userByEmail(Model model) {
		List<User> users = repository.findAll();
		return (users.get(0));
	}
}
