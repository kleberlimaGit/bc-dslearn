package com.bc.devsuperior.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bc.devsuperior.dslearn.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
