package com.mcq.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import com.mcq.webapp.model.Note;
import com.mcq.webapp.model.TeacherDetailsModel;
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
public class TeacherController {
    @Autowired
    TeacherDetailsRepository teacherDetailsRepository;

    @GetMapping("/teacher")
    public List<TeacherDetailsModel> getAllTeachers() {
        return teacherDetailsRepository.findAll();
    }
    public TeacherDetailsModel findByUsername(String username) {
		return teacherDetailsRepository.findByFkusername(username);
    }
    @PostMapping("/teacher")
    public TeacherDetailsModel createTeacher(@Valid @RequestBody TeacherDetailsModel teacherDetailsModel) {
        TeacherDetailsModel us= findByUsername(teacherDetailsModel.getFkusername());
        if(us!=null){
            throw new SecurityException("Username is exists");
        }
        
        return teacherDetailsRepository.save(teacherDetailsModel);
    }

    @GetMapping("/teacher/{username}")
    public TeacherDetailsModel getTeacherById(@PathVariable(value = "username") String username) {
        return teacherDetailsRepository.findByFkusername(username);
               
    }

    @PutMapping("/teacher/{username}")
    public TeacherDetailsModel updateTeacher(@PathVariable(value = "username") String username,
                                           @Valid @RequestBody TeacherDetailsModel  teacherDetailsModel) {

        TeacherDetailsModel teacher = teacherDetailsRepository.findByFkusername(username);
                // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        teacher.setAddressline1(teacherDetailsModel.getAddressline1());
        teacher.setAddressline2(teacherDetailsModel.getAddressline2());
        teacher.setCity(teacherDetailsModel.getCity());
        teacher.setCreatedAt(teacherDetailsModel.getCreatedAt());
        teacher.setDistrict(teacherDetailsModel.getDistrict());
        teacher.setWorkplace(teacherDetailsModel.getWorkplace());

        TeacherDetailsModel teacherModel = teacherDetailsRepository.save(teacher);
        return teacherModel;
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable(value = "id") String username) {
        TeacherDetailsModel teachers = teacherDetailsRepository.findByFkusername(username);
                // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        teacherDetailsRepository.delete(teachers);

        return ResponseEntity.ok().build();
    }
}