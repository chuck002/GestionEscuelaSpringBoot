package com.javy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.javy.entity.Teacher;
import com.javy.services.TeacherServiceImplementation;

@Controller
@RequestMapping("/admin/teacher")
public class TeacherController {

	@Autowired
	private TeacherServiceImplementation service;

	@RequestMapping("/listar")
	public String getTeachers(Model modelo) {
		modelo.addAttribute("teachers", service.getTeachers());

		return "administrator/teacher_listar";
	}

	@RequestMapping("/editar/{id}")
	public String getTeacher(@PathVariable("id") Integer id, Model modelo) {
		modelo.addAttribute("teacher", service.getTeacher(id));

		return "administrator/teacher_editar";
	}

	@RequestMapping("/add")
	public String add(Teacher teacher) {

		return "administrator/teacher_agregar";
	}

	@RequestMapping("/addTeacher")
	public String addTeacher(@Valid Teacher teacher, BindingResult result, Model modelo) {
		if (result.hasErrors()) {

			return "administrator/teacher_agregar";
		}

		service.addTeacher(teacher);
		modelo.addAttribute("teachers", service.getTeachers());
		return "administrator/teacher_listar";
	}

	@RequestMapping("/update/{id}")
	public String updateTeacher(@PathVariable("id") Integer id, @Valid Teacher teacher, BindingResult result,
			Model modelo) {

		if (result.hasErrors()) {
			teacher.setId(id);
			modelo.addAttribute("teacher", teacher);

			return "administrator/teacher_editar";
		}

		service.updateTeacher(teacher);

		modelo.addAttribute("teachers", service.getTeachers());

		return "/administrator/teacher_listar";
	}

	@RequestMapping("/delete/{id}")
	public String deleteTeacher(@PathVariable("id") Integer id, Model modelo) {
		 service.removeTeacher(id);
		 modelo.addAttribute("teachers", service.getTeachers());	
			
			return "/administrator/teacher_listar";
	}

}
