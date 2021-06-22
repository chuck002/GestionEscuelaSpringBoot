package com.javy.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javy.entity.Teacher;
import com.javy.repository.TeacherRepository;

@Service
public class TeacherServiceImplementation implements TeacherService {

	@Autowired
	private TeacherRepository repository;
	
	@Override
	public List<Teacher> getTeachers() {
		
		return repository.findAll();
	}

	@Override
	public void addTeacher(Teacher teacher) {
		repository.save(teacher);
	}

	@Override
	public void removeTeacher(int id) {
		repository.deleteById(id);
	}

	@Override
	public Teacher getTeacher(int id) {
		return repository.findById(id).get();
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		repository.save(teacher);

	}

}
