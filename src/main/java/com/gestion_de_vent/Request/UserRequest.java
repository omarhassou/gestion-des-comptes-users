package com.gestion_de_vent.Request;

import java.util.List;


public class UserRequest {
      
	private String firstname;
	private String lastname;
	   
	private String email;
	private String password;
	private List<AddressRequest> addreses;
	private ContactRequest contact;
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
	public List<AddressRequest> getAddreses() {
		return addreses;
	}
	public void setAddreses(List<AddressRequest> addreses) {
		this.addreses = addreses;
	}
	public ContactRequest getContact() {
		return contact;
	}
	public void setContact(ContactRequest contact) {
		this.contact = contact;
	}
	

}
