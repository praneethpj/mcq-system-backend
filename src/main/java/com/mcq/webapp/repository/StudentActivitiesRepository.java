package com.mcq.webapp.repository;

 
import com.mcq.webapp.model.StudentActivitiesModel;
import com.mcq.webapp.model.Subject;
import com.mcq.webapp.model.TeacherActivitiesModel;
import com.mcq.webapp.model.Term;
import com.mcq.webapp.model.Type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentActivitiesRepository extends JpaRepository<StudentActivitiesModel, Long> {
    StudentActivitiesModel findByFkusername(String fkusername);
}