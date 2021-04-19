package com.example.apparel.security.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AppUserDetailsServiceInt {
	 AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	}
