package com.gestion_de_vent.shered.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4077628720771988754L;
	private Long id;
	private String userid;


	
	private String firstname;
	private String lastname;
	private String email;
	private String password;  
	private Boolean admin;
	private String encryptedpassword; 
	private String emailVerificationToken;
	private Boolean emailVerificationStatuse = false;
	private List<AddressDto> addreses;
	private ContactDto contact;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedpassword() {
		return encryptedpassword;
	}
	public void setEncryptedpassword(String encryptedpassword) {
		this.encryptedpassword = encryptedpassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean getEmailVerificationStatuse() {
		return emailVerificationStatuse;
	}
	public void setEmailVerificationStatuse(Boolean emailVerificationStatuse) {
		this.emailVerificationStatuse = emailVerificationStatuse;
	}
	public List<AddressDto> getAddreses() {
		return addreses;
	}
	public void setAddreses(List<AddressDto> addreses) {
		this.addreses = addreses;
	}
	public ContactDto getContact() {
		return contact;
	}
	public void setContact(ContactDto contact) {
		this.contact = contact;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	
	

	
	
}
