package com.javy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Table(name = "course")
public class Course implements Comparable<Course> {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column (name = "name_course", length = 50)
	@NotBlank (message = "Nombre del curso no puede estar vacio.")
	private String name_course;
	@Column (name = "schedule_course", length = 15)
	@NotBlank (message = "El horario no puede estar vacio.")
	private String schedule_course;
	@Column (name = "features", length = 2000)
	private String features;
	@Column (name = "max_student")
	@NotNull (message = "La cantidad de estudiantes no puede estar vacio.")
	@Min (value = 10, message = "La cantidad de estudiantes no puede ser menor a 10.")
	@Max (value = 40, message = "La cantidad de estudiantes no puede ser mayor a 40.")
	private int max_student;
	@ManyToOne(targetEntity = Teacher.class)
	@JoinColumn(name="id_teacher")
	private Teacher teacher;
	@ManyToMany(mappedBy = "courses")
	private List<Users> users;
	
	
	
	public Course() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName_course() {
		return name_course;
	}

	public void setName_course(String name_course) {
		this.name_course = name_course;
	}

	public String getSchedule_course() {
		return schedule_course;
	}

	public void setSchedule_course(String schedule_course) {
		this.schedule_course = schedule_course;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getMax_student() {
		return max_student;
	}

	public void setMax_student(int max_student) {
		this.max_student = max_student;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	@Override
	public int compareTo(Course o) {
		int res = this.name_course.compareTo(o.name_course);
		return res;
	}
	
	
}
