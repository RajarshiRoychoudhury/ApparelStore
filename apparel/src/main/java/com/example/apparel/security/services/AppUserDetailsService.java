package com.example.apparel.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apparel.model.User;
import com.example.apparel.repository.UserRepository;


@Service
public class AppUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return AppUserDetails.build(user);
	}

}