package com.mcq.webapp.controller;

import com.mcq.webapp.dao.UserDao;
import com.mcq.webapp.exception.ResourceNotFoundException;

import com.mcq.webapp.model.Users;

import com.mcq.webapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;
 
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
 

@RestController
@RequestMapping("/api")
public class UserController implements UserDetailsService {

   
    @Autowired
    private UserRepository userdao;

    // @Autowired
    // private BCryptPasswordEncoder passEncoder;
    

    @Autowired
    UserRepository userRepo;

    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    @PostMapping("/gettest")
    public String gettest() {
        return "working";
    }

    @GetMapping("/getAllregistration")
    public List<Users> getAllNotes() {
        return userRepo.findAll();
    }
    
    @PostMapping("/login")
    public List<Users> userlogin(@Valid @RequestBody String username,@Valid @RequestBody String password) {
        return userRepo.findAll();
    }
    
    

    @PostMapping("/registration")
    public Users createUser(@Valid @RequestBody Users user) {

        
       Users us= findByUsername(user.getUsername());
       if(us!=null){
           throw new SecurityException("Username is exists");
       }
    
       String pass=user.getPassword();
     BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
       String hashedPassword = passEncoder.encode(pass);

       user.setPassword(hashedPassword);
       return userRepo.save(user);
      
       
    }
    public Users findByUsername(String username) {
		return userRepo.findByUsername(username);
    }
    public Users findByPassword(String username) {
		return userRepo.findByPassword(username);
    }
  

    // @PostMapping("/login")
    // public Users login(@Valid @RequestBody String username,String password) {
    // return userRepo.save(user);
    // }

    @GetMapping("/users/{id}")
    public Users getUserById(@PathVariable(value = "id") Long userId) {
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody Users userDetails) {

        Users user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setFirst_name(userDetails.getFirst_name());
        user.setCreatedAt(userDetails.getCreatedAt());
        user.setUpdatedAt(userDetails.getUpdatedAt());
        user.setLast_name(userDetails.getLast_name());
        user.setAddress_line1(userDetails.getAddress_line1());
        user.setAddress_line2(userDetails.getAddress_line2());
        user.setCity(userDetails.getCity());
        user.setRole(userDetails.getRole());
        user.setMobile_num1(userDetails.getMobile_num1());
        user.setMobile_num2(userDetails.getMobile_num2());
        user.setPassword(userDetails.getPassword());
        user.setEmail_address(userDetails.getEmail_address());

        Users updateUsers = userRepo.save(user);
        return updateUsers;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long userId) {
        Users user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepo.delete(user);

        return ResponseEntity.ok().build();
    }

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
       
        
        //Users us= loadUserByUsername(arg0);
        Users user=userdao.findByUsername(arg0);
        if(user==null){
            throw new UsernameNotFoundException("Username is not exists");
        }
        // String pass=findByEmail(username)
        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // String hashedPassword = passwordEncoder.encode(pass);
 
        // user.setPassword(hashedPassword);
    
        //Users user = findByUsernamePassword(username, password);
		// if (user == null) {
		// 	throw new UsernameNotFoundException("User not found with username: " + arg0);
		// }

        // System.out.println("USername  "+arg0);
        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passEncoder.encode(user.getPassword());
        System.out.println("Password  "+hashedPassword);
        return new User(user.getUsername(), hashedPassword, new ArrayList<>());
    }

    
}




  