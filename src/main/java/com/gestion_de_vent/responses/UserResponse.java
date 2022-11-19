package com.gestion_de_vent.responses;


import java.util.List;

public class UserResponse {

	private String userid;
	private String firstname;
	private String lastname;
	private String email;
	private Boolean admin;
private List<AddressResponse> addreses;
private  ContactResponse contact;

	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
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

	public List<AddressResponse> getAddreses() {
		return addreses;
	}

	public void setAddreses(List<AddressResponse> addreses) {
		this.addreses = addreses;
	}

	public ContactResponse getContact() {
		return contact;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public void setContact(ContactResponse contact) {
		this.contact = contact;
	}



}

