
package com.javy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Table(name = "teacher")
public class Teacher {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", length = 15)
	@NotBlank (message = "Nombre no puede estar vacio.")
	private String name;
	@Column(name = "last_name", length = 15)
	@NotBlank (message = "Apellido no puede estar vacio.")
	private String last_name;
	@Column (name = "dni")
	@NotNull (message = "DNI no puede estar vacio.")
	@Min(value = 5000000, message = "El dni no puede ser un numero menor a 5 millones.")
    @Max(value = 70000000, message = "El dni no puede ser un numero mayor a 70 millones")
	private int dni;
	@Column (name = "enabled")
	private int enabled;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
	private List<Course> courses;
	
	
	public Teacher() {
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

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", last_name=" + last_name + ", dni=" + dni + ", enabled="
				+ enabled + ", courses=" + courses + "]";
	}

	
	
}
