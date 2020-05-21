
package com.mcq.webapp.repository;

import com.mcq.webapp.model.AdminDetailsModel;
import com.mcq.webapp.model.TeacherDetailsModel;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminDetailsRepository extends JpaRepository<AdminDetailsModel, Long> {
AdminDetailsModel findByFkusername(String fkusername);
}