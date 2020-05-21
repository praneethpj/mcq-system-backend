package com.mcq.webapp.repository;

 
import com.mcq.webapp.model.Type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}