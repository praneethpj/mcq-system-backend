
package com.mcq.webapp.repository;

import com.mcq.webapp.model.StudentDetailsModel;
 
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetailsModel, Long> {
      StudentDetailsModel findByFkusername(String fkusername);
}