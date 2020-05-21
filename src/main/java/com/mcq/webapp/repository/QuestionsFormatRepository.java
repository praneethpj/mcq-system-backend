package com.mcq.webapp.repository;
 
import com.mcq.webapp.model.QuestionFormat;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 

@Repository
public interface QuestionsFormatRepository extends JpaRepository<QuestionFormat, Long> {

}
