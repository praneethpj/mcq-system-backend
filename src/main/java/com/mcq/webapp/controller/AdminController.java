package com.mcq.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import com.mcq.webapp.model.AdminDetailsModel;
import com.mcq.webapp.model.Note;
import com.mcq.webapp.model.TeacherDetailsModel;
import com.mcq.webapp.repository.AdminDetailsRepository;
import com.mcq.webapp.repository.TeacherDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    AdminDetailsRepository adminDetailsRepository;

    @GetMapping("/admin")
    public List<AdminDetailsModel> getAllAdmin() {
        return adminDetailsRepository.findAll();
    }
    public AdminDetailsModel findByUsername(String username) {
		return adminDetailsRepository.findByFkusername(username);
    }
    @PostMapping("/admin")
    public AdminDetailsModel createTeacher(@Valid @RequestBody AdminDetailsModel adminDetailsModel) {
        AdminDetailsModel us= findByUsername(adminDetailsModel.getFkusername());
        if(us!=null){
            throw new SecurityException("Username is exists");
        }
        
        return adminDetailsRepository.save(adminDetailsModel);
    }

    @GetMapping("/admin/{username}")
    public AdminDetailsModel getAdminById(@PathVariable(value = "username") String username) {
        return adminDetailsRepository.findByFkusername(username);
               
    }

    @PutMapping("/admin/{username}")
    public AdminDetailsModel updateAdmin(@PathVariable(value = "username") String username,
                                           @Valid @RequestBody AdminDetailsModel adminDetailsModel) {

        AdminDetailsModel admin = adminDetailsRepository.findByFkusername(username);
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        admin.setAddressline1(adminDetailsModel.getAddressline1());
        admin.setAddressline2(adminDetailsModel.getAddressline2());
        admin.setCity(adminDetailsModel.getCity());
        admin.setCreatedAt(adminDetailsModel.getCreatedAt());
        admin.setDistrict(adminDetailsModel.getDistrict());
    
        AdminDetailsModel adminModel = adminDetailsRepository.save(admin);
        return adminModel;
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") String username) {
        AdminDetailsModel adminDetailsModel = adminDetailsRepository.findByFkusername(username);
                // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

                adminDetailsRepository.delete(adminDetailsModel);

        return ResponseEntity.ok().build();
    }
}