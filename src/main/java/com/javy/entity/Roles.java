package com.javy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(targetEntity = Users.class)
	@JoinColumn(name="user_id")
	private Users user_id;
	@Column(name = "roles", length = 15)
	private String roles;
	
	public Roles() {
		
	}
	public Roles(Users user, String rol) {
		this.user_id = user;
		this.roles = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return roles;
	}

	public void setRol(String rol) {
		this.roles = rol;
	}

	
	@Override
	public String toString() {
		return "Rol [id=" + id + ", rol=" + roles + "]";
	}

}
