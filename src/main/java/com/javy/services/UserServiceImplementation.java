package com.javy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javy.entity.Users;
import com.javy.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public List<Users> getUsers() {

		return repository.findAll();
	}

	@Override
	public Users getUser(int id) {
		
		return repository.getById(id);
	}

	@Override
	public void addUser(Users user) {
		
		repository.save(user);
		
	}

	@Override
	public void removeUser(int id) {
		
		repository.deleteById(id);
		
	}

	@Override
	public void updateUser(Users user) {
		
		repository.save(user);
		
	}
	
	}
