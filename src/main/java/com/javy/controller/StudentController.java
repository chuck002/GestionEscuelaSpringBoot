package com.javy.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javy.entity.Course;
import com.javy.entity.Users;
import com.javy.services.CourseServiceImplementation;
import com.javy.services.UserServiceImplementation;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private UserServiceImplementation student;
	@Autowired
	private CourseServiceImplementation course;
	
	
	
	private Users usuarioLogueado() {
		
	    Users uTmp = new Users();
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (user instanceof UserDetails) {
		  userDetails = (UserDetails) user;
		}
		List<Users> users = student.getUsers();
		for(Users u : users) {
			if(u.getUsername_user().equals(userDetails.getUsername())) {
				uTmp = student.getUser(u.getId());
			}
		}
		return uTmp;
	}

	@RequestMapping("/mostrar")
	public String getStudent(Model modelo) {
		Users user = usuarioLogueado();
		modelo.addAttribute("user", user);
		
		return "student/student_mostrar";
	}
	@RequestMapping("/mostrar_materias")
	public String getCourses(Model modelo) {
		List<Course> courses = course.getCourses();
		Collections.sort(courses);
		modelo.addAttribute("courses", courses);
		
		return "student/student_materias";
	}
	
	@RequestMapping("mostrar_materia/{id}")
	public String getCourse(Model modelo, @PathVariable("id") Integer id) {
		modelo.addAttribute("course", course.getCourse(id));
		Users user = usuarioLogueado();
		boolean esta = false;
		if(user.getCourses() != null) {
		for(Course course : user.getCourses()) {
			if(course.getId() == this.course.getCourse(id).getId()) {
				esta = true;
			}
		}
		}
		modelo.addAttribute("esta", esta);
		
		modelo.addAttribute("user", user);
		
		return "student/student_programa";
	}
	
	@RequestMapping("inscribir_materia/{id}")
	public String addCourse(Model modelo, @PathVariable("id") Integer id) {
		List<Course> courses;
		Users user = usuarioLogueado();
		int total = course.getCourse(id).getMax_student();
		System.out.println(total);
		if(total == 0) {
			return "student/student_materia_completa";
		}
		total--;
		course.getCourse(id).setMax_student(total);
		if(user.getCourses() == null) {
			courses = new ArrayList<>();
		}else{
			courses = user.getCourses();
		}
		courses.add(course.getCourse(id));
		user.setCourses(courses);
		course.addCourse(course.getCourse(id));
		student.addUser(user);
		modelo.addAttribute("courses", course.getCourses());
		modelo.addAttribute("user", user);
		return "student/student_materias";
	}
	
	
}
