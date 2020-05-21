package com.mcq.webapp.dao;

import com.mcq.webapp.model.Users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 

@Repository
public interface UserDao extends CrudRepository<Users, Long> {
}