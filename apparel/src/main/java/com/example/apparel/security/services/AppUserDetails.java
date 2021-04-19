package com.example.apparel.security.services;


import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.example.apparel.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;

public class AppUserDetails implements UserDetails {

	/**
	 * 
	 */
	
	private String Id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String gender;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public AppUserDetails(String id, String firstname, String lastname, String email, String password, String gender,
			Collection<? extends GrantedAuthority> authorities) {
		this.Id = id;
		this.username = email;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.lastname = lastname;
		this.gender  = gender;
	}
	
	public static AppUserDetails build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new AppUserDetails(
				user.getId(),
				user.getFirstname(),
				user.getLastname(), 
				user.getEmail(),
				user.getPassword(),
				user.getGender(),
				authorities);
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUserDetails other = (AppUserDetails) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	public String getId() {
		return Id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



}
