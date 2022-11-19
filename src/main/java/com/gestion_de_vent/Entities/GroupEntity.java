package com.gestion_de_vent.Entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
@Entity(name = "groups")
public class GroupEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7916817251183132078L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "name", length = 30)
	private String name;
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name ="groups_users",joinColumns = {@JoinColumn(name ="groups_id")},inverseJoinColumns = {@JoinColumn(name ="users_id")})
	private  Set<UserEntity> user = new HashSet<>(); 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<UserEntity> getUser() {
		return user;
	}
	public void setUser(Set<UserEntity> user) {
		this.user = user;
	}
    
}
