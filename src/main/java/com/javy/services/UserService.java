package com.javy.services;

import java.util.List;

import com.javy.entity.Users;

public interface UserService {
	
	public List<Users> getUsers();
	public Users getUser(int id);
	public void addUser(Users user);
	public void removeUser(int id);
	public void updateUser(Users user);


}
