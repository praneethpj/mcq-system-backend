package com.mcq.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import com.mcq.webapp.model.TeacherActivitiesModel;
import com.mcq.webapp.model.TeacherDetailsModel;
import com.mcq.webapp.repository.TeacherActivitiesRepository;
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
public class TeachersActivityController {
    @Autowired
    TeacherActivitiesRepository teacherActivitiesRepository;

    @GetMapping("/teachersactivities")
    public List<TeacherActivitiesModel> getAllTeachersActivity() {
        return teacherActivitiesRepository.findAll();
    }
    public TeacherActivitiesModel findByUsername(String username) {
		return teacherActivitiesRepository.findByFkusername(username);
    }
    @PostMapping("/teachersactivities")
    public TeacherActivitiesModel createTeacher(@Valid @RequestBody TeacherActivitiesModel teacherActivitiesModel) {
        TeacherActivitiesModel us= findByUsername(teacherActivitiesModel.getFkusername());
        if(us!=null){
            throw new SecurityException("Username is exists");
        }
        
        return teacherActivitiesRepository.save(teacherActivitiesModel);
    }

    @GetMapping("/teachersactivities/{username}")
    public TeacherActivitiesModel getTeacherById(@PathVariable(value = "username") String username) {
        return teacherActivitiesRepository.findByFkusername(username);
               
    }

    @PutMapping("/teachersactivities/{username}")
    public TeacherActivitiesModel updateTeacher(@PathVariable(value = "username") String username,
                                           @Valid @RequestBody TeacherActivitiesModel  teacherDetailsModel) {

    TeacherActivitiesModel teacher = teacherActivitiesRepository.findByFkusername(username);
                // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        teacher.setFkquestionformatid(teacherDetailsModel.getFkquestionformatid());
        teacher.setFkquestionid(teacherDetailsModel.getFkquestionid());
        teacher.setVisibility(teacherDetailsModel.getVisibility());
        teacher.setCreatedAt(teacherDetailsModel.getCreatedAt());
        teacher.setUpdatedAt(teacherDetailsModel.getUpdatedAt());
    
        TeacherActivitiesModel teacherModel = teacherActivitiesRepository.save(teacher);
        return teacherModel;
    }

    @DeleteMapping("/teachersactivities/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable(value = "id") String username) {
        TeacherActivitiesModel teachers = teacherActivitiesRepository.findByFkusername(username);
                // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

                teacherActivitiesRepository.delete(teachers);

        return ResponseEntity.ok().build();
    }
}