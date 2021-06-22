package com.javy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javy.entity.Course;
import com.javy.repository.CoursesRepository;

@Service
public class CourseServiceImplementation implements CourseService {

	@Autowired
	private CoursesRepository repository;
	
	@Override
	public List<Course> getCourses() {
		return repository.findAll();
	}

	@Override
	public Course getCourse(int id) {
		return repository.getById(id);
	}

	@Override
	public void addCourse(Course course) {
		repository.save(course);
	}

	@Override
	public void removeCourse(int id) {
		repository.deleteById(id);
	}

	@Override
	public void updateCourse(Course course) {
		repository.save(course);
	}

}
