package com.bc.devsuperior.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bc.devsuperior.dslearn.entities.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long>{

}
