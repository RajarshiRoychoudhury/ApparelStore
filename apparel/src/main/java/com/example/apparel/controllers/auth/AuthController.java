package com.example.apparel.controllers.auth;


import java.util.ArrayList;
import java.util.Arrays;
import com.example.apparel.repository.CartRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apparel.api.request.LoginRequest;
import com.example.apparel.api.request.SignupRequest;
import com.example.apparel.api.response.JwtResponse;
import com.example.apparel.api.response.MessageResponse;
import com.example.apparel.model.Cart;
import com.example.apparel.model.CartItemFull;
import com.example.apparel.model.ERole;
import com.example.apparel.model.Item;
import com.example.apparel.model.ListSearchHistory;
import com.example.apparel.model.Role;
import com.example.apparel.model.User;
import com.example.apparel.repository.RoleRepository;
import com.example.apparel.repository.ItemRepository;
import com.example.apparel.repository.UserRepository;
import com.example.apparel.repository.ListSearchHistoryRepository;
import com.example.apparel.security.jwt.JwtUtils;
import com.example.apparel.security.services.AppUserDetails;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ListSearchHistoryRepository lshrepository;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		System.out.println(loginRequest);
		Authentication authentication = authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		System.out.println(cartRepository);
		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();	
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		System.out.println(roles);
		Cart cart = cartRepository.findByUserId(userDetails.getId()).get(0);
		List<CartItemFull> cart_items = new ArrayList<>();
		for(int i=0;i<cart.getCartItems().size();i++) {
			Item item = itemRepository.findById(cart.getCartItems().get(i).getId()).get();
			cart_items.add(new CartItemFull(item,cart.getCartItems().get(i).getQuantity()));
		}
		System.out.println("Hi");
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles,
												 cart_items));
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
			
		System.out.println(signUpRequest);
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
		User user = new User(signUpRequest.getFirstname(), 
							 signUpRequest.getLastname(),
							 signUpRequest.getGender(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		Cart cart = new Cart(user.getId(),new ArrayList<>());
		ListSearchHistory lsh = new ListSearchHistory(user.getId());
		lshrepository.save(lsh);
		cartRepository.save(cart);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/logout")
	public ResponseEntity<?>logout (HttpServletRequest req){
		List<String> items = Arrays.asList("Shoe","Top","Skirt","Pant","Shirt","Tshirt");
		for(int i=0;i<items.size();i++) {
			req.getSession().removeAttribute(items.get(i));
			
		}
		req.getSession().invalidate();
		return ResponseEntity.ok(new MessageResponse("Successfully logged out"));
	}
	
}
