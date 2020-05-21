package com.mcq.webapp.repository;

import com.mcq.webapp.model.Users;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
   //  public Users findByUsername(String email);
     public Users findByUsername(String username);
      // UserRepository findUsername(String username);
     public Users findByPassword(String password);
    // public Users findByUsernamePassword(String username,String password);
}