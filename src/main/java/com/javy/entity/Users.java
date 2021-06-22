package com.javy.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", length = 25)
	private String name;
	@Column(name = "last_name", length = 25)
	private String last_name;
	@Max(value = 10000000, message = "El legajo debe ser menor a 10 millones")
	@Column(name = "username_user", length = 15)
	private String username_user;
	@Column(name = "password_user", length = 60)
	private String password_user;
	@Column(name = "enabled")
	private int enabled;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user_id")
	private List<Roles> roles;
	@JoinTable(
	        name = "users_courses",
	        joinColumns = @JoinColumn (name = "FK_USER", nullable = false),
	        inverseJoinColumns = @JoinColumn (name="FK_COURSE", nullable = false)
	    )
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Course> courses;
	

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Users() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername_user() {
		return username_user;
	}

	public void setUsername_user(String username_user) {
		this.username_user = username_user;
	}

	public String getPassword_user() {
		return password_user;
	}

	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public boolean hasRole(String rol) {
		Iterator<Roles> iterator = this.roles.iterator();
		while (iterator.hasNext()) {
			Roles role = iterator.next();
			if (role.getRol().equals(rol)) {
				return true;
			}
		}

		return false;
	}

}
