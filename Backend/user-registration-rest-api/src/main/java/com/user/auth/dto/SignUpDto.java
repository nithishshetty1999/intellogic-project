package com.user.auth.dto;

import jakarta.validation.constraints.*;


public class SignUpDto {
    @Pattern(regexp="([0-9]{10})", message = "Phone number should be of 10 digits with only numbers")
    private String contact;
    @NotBlank(message = "username should not be blank or empty")
    private String username;
    @Email(message = "email id is not in the correct format")
    private String email;
    @NotBlank(message = "password should not be blank  or empty")
    private String password;
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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