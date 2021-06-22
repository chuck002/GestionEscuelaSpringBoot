package com.javy.services;

import java.util.List;

import com.javy.entity.Teacher;

public interface TeacherService {

	public List<Teacher> getTeachers();
	public void addTeacher(Teacher teacher);
	public void removeTeacher(int id);
	public Teacher getTeacher(int id);
	public void updateTeacher(Teacher teacher);
	
}
