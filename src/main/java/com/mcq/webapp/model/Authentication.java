package com.mcq.webapp.model;

import java.util.Set;

import javax.management.relation.Role;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;

public class Authentication {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;

    private String password;
    private String userLevel;

    @ManyToMany
    private Set<Role> roles;

    public Authentication (){

    }
    public Authentication (String usernae,String password){
        
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getUserLevel(){
        return userLevel;
    }

    public Set getUserRoles(){
      return roles;
  }

    public void setUsername(String username){
      this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
      }
      public void setLevel(String userlevel){
        this.userLevel=userlevel;
      }
      public void setRoles(Set roles){
        this.roles=roles;
      }
      
}