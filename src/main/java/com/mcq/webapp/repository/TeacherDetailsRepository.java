
package com.mcq.webapp.repository;

import com.mcq.webapp.model.TeacherDetailsModel;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherDetailsRepository extends JpaRepository<TeacherDetailsModel, Long> {
      TeacherDetailsModel findByFkusername(String fkusername);
}