package com.javy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javy.entity.Roles;
import com.javy.repository.RolesRepository;

@Service
public class RolesServiceImplementation implements RolesService {

	@Autowired
	private RolesRepository repository;
	
	@Override
	public Roles getRol(int id) {
		return repository.getById(id);
	}

	@Override
	public void setRol(Roles rol) {
		repository.save(rol);
	}

}
