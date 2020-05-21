package com.mcq.webapp.repository;
 
import com.mcq.webapp.model.Questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {

}
