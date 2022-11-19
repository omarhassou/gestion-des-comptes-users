package com.gestion_de_vent.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity(name = "contacts")
public class ContactEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3684529955348018655L;
@Id	
@GeneratedValue
private Long  id;

@Column(length =30, nullable =false)
private String contactId;
@Column(length =30, nullable =false)
private String mobile;
@Column(length =30, nullable =false)
private String  skype;
@OneToOne
@JoinColumn(name = "users_id")
private UserEntity user;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getContactId() {
	return contactId;
}
public void setContactId(String contactId) {
	this.contactId = contactId;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getSkype() {
	return skype;
}
public void setSkype(String skype) {
	this.skype = skype;
}
public UserEntity getUser() {
	return user;
}
public void setUser(UserEntity user) {
	this.user = user;
}



}
