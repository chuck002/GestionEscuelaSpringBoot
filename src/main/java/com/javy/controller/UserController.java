package com.javy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javy.entity.Roles;
import com.javy.entity.Users;
import com.javy.services.UserServiceImplementation;

@Controller
@RequestMapping("/admin/student")
public class UserController {
	
	@Autowired
	private UserServiceImplementation service;
	@Autowired
	private BCryptPasswordEncoder encoder;

	
	@RequestMapping("/listar")
	public String getStudents(Model modelo) {
		modelo.addAttribute("users", service.getUsers());

		return "administrator/student_listar";
	}

	@RequestMapping("/editar/{id}")
	public String getStudent(@PathVariable("id") Integer id, Model modelo) {
		modelo.addAttribute("user", service.getUser(id));

		return "administrator/student_editar";
	}

	@RequestMapping("/add")
	public String add(Users user, Model modelo) {
		modelo.addAttribute("user", user);
		return "administrator/student_agregar";
	}

	@RequestMapping("/addStudent")
	public String addStudent(@Valid Users user, BindingResult result, Model modelo) {
		if (result.hasErrors()) {

			return "administrator/student_agregar";
		}
			
		String pass = encoder.encode(user.getPassword_user());
		user.setPassword_user(pass);
		List<Roles> roles = new ArrayList<>();
		Roles rolStudent = new Roles(user, "ROLE_STUDENT");
		roles.add(rolStudent);
		user.setRoles(roles);
		service.addUser(user);
		modelo.addAttribute("users", service.getUsers());
		return "administrator/student_listar";
	}

	@RequestMapping("/update/{id}")
	public String updateStudent(@PathVariable("id") Integer id, @Valid Users user, BindingResult result,
			Model modelo) {

		if (result.hasErrors()) {
			user.setId(id);
			modelo.addAttribute("user", user);
			System.out.println("Se queda en el if");
			return "administrator/student_editar";
		}

		System.out.println("LLegamos a update");
		service.updateUser(user);

		modelo.addAttribute("users", service.getUsers());

		return "/administrator/student_listar";
	}

	@RequestMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") Integer id, Model modelo) {
		 service.removeUser(id);
		 modelo.addAttribute("users", service.getUsers());	
			
			return "/administrator/student_listar";
	}

}
