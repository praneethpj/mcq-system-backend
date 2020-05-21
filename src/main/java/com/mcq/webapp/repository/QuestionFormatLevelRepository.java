package com.mcq.webapp.repository;
 
import com.mcq.webapp.model.QuestionFormat;
import com.mcq.webapp.model.QuestionFormatLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 

@Repository
public interface QuestionFormatLevelRepository extends JpaRepository<QuestionFormatLevel, Long> {

}
