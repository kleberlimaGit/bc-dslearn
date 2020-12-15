package com.bc.devsuperior.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bc.devsuperior.dslearn.entities.Lesson;
import com.bc.devsuperior.dslearn.entities.pk.EnrollmentPK;

public interface EnrollmentRepository extends JpaRepository<Lesson, EnrollmentPK>{

}
