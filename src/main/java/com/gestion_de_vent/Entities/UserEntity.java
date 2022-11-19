package com.gestion_de_vent.Entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity(name = "users")
public class UserEntity implements Serializable {

	
	private static final long serialVersionUID = -4342207223054584977L;
	@Id
	@GeneratedValue
	private Long id; 
	@Column(nullable = false)
	private String userid;
	@Column(nullable = false, length=30)
	private String firstname;
	@Column(nullable = false,length=30)
	private String lastname;
	@Column(nullable=false ,length=130, unique = true )
	private String email;
	@Column(nullable=true) 
	private Boolean admin =false;
	@Column(nullable=false) 
	private String encryptedpassword;
	@Column(nullable=true)
	private String emailVerificationToken;
	//@Column(columnDefinition=" boolean default false") 
	@Column(nullable=false )
	private Boolean emailVerificationStatuse = false;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	List<AddressEntity> addreses;
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private ContactEntity contact;
	@ManyToMany(fetch =FetchType.LAZY, cascade =CascadeType.ALL,mappedBy = "user")
	private Set<GroupEntity> group =new HashSet<>();
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
	public List<AddressEntity> getAddreses() {
		return addreses;
	}
	public void setAddreses(List<AddressEntity> addreses) {
		this.addreses = addreses;
	}
	public ContactEntity getContact() {
		return contact;
	}
	public void setContact(ContactEntity contact) {
		this.contact = contact;
	}
	public Set<GroupEntity> getGroup() {
		return group;
	}
	public void setGroup(Set<GroupEntity> group) {
		this.group = group;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	

}
