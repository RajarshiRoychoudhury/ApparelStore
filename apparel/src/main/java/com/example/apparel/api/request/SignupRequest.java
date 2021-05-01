package com.example.apparel.api.request;

import java.util.Set;

import com.example.apparel.model.Role;


 
public class SignupRequest {

 

    private String email;
    private String firstname;
    private String lastname;
    private String gender;
    private String password;
    private Set<String> roles;
    
    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

    
 
  

 
    public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "SignupRequest [email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", gender="
				+ gender + ", password=" + password + ", roles=" + roles + "]";
	}

	public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    

}