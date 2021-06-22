package com.javy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javy.entity.Course;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Integer> {

}
