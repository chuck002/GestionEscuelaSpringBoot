package com.javy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javy.entity.Course;
import com.javy.services.CourseServiceImplementation;
import com.javy.services.TeacherServiceImplementation;

@Controller
@RequestMapping("/admin/course")
public class CourseController {

	@Autowired
	private CourseServiceImplementation service;
	
	@Autowired
	private TeacherServiceImplementation serviceTeacher;
	
	List<String> Horarios = new ArrayList<>();
	
	private List<String> getHorarios(){
		Horarios.add("08:00 - 10:00");
		Horarios.add("10:00 - 12:00");
		Horarios.add("12:00 - 14:00");
		Horarios.add("14:00 - 16:00");
		Horarios.add("16:00 - 18:00");
		Horarios.add("18:00 - 20:00");
		Horarios.add("20:00 - 22:00");
		Horarios.add("22:00 - 00:00");
		return Horarios;
	}
	

	@RequestMapping("/listar")
	public String getCourses(Model modelo) {
		modelo.addAttribute("courses", service.getCourses());

		return "administrator/course_listar";
	}
	
	@RequestMapping("/editar/{id}")
	public String getCourse(@PathVariable("id") Integer id, Model modelo) {
		modelo.addAttribute("course", service.getCourse(id));

		return "administrator/course_editar";
	}

	@RequestMapping("/update/{id}")
	public String updateCourse(@PathVariable("id") Integer id, @Valid Course course, BindingResult result,
			Model modelo) {
		if (result.hasErrors()) {
			course.setId(id);
			modelo.addAttribute("course", course);

			return "administrator/course_editar";
		}

		service.updateCourse(course);

		modelo.addAttribute("courses", service.getCourses());

		return "/administrator/course_listar";
	}

	@RequestMapping("/add")
	public String add(Course course, Model modelo) {
				
				modelo.addAttribute("horarios", getHorarios());
				modelo.addAttribute("teachers", serviceTeacher.getTeachers());

		return "/administrator/course_agregar";
	}

	@RequestMapping("/addCourse")
	public String addCourse(@Valid Course course, BindingResult result, Model modelo) {
		if (result.hasErrors()) {
			modelo.addAttribute("horarios", getHorarios());
			modelo.addAttribute("teachers", serviceTeacher.getTeachers());
			return "administrator/course_agregar";
		}

		service.addCourse(course);
		modelo.addAttribute("courses", service.getCourses());
		return "administrator/course_listar";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteCourse(@PathVariable("id") Integer id, Model modelo) {
		service.removeCourse(id);
		modelo.addAttribute("courses", service.getCourses());

		return "/administrator/course_listar";
	}
}
