package com.mcq.webapp.repository;

 
import com.mcq.webapp.model.Grade;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findByGrade(String grade);
}