package com.bc.devsuperior.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bc.devsuperior.dslearn.entities.Enrollment;

public interface LessonRepository extends JpaRepository<Enrollment, Long>{

}
