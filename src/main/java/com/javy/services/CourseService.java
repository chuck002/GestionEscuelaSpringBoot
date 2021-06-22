package com.javy.services;

import java.util.List;
import com.javy.entity.Course;

public interface CourseService {
	
	public List<Course> getCourses();
	public Course getCourse(int id);
	public void addCourse(Course course);
	public void removeCourse(int id);
	public void updateCourse(Course course);

}
