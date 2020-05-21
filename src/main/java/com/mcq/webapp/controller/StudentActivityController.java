package com.mcq.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import com.mcq.webapp.model.StudentActivitiesModel;
import com.mcq.webapp.model.TeacherActivitiesModel;
import com.mcq.webapp.model.TeacherDetailsModel;
import com.mcq.webapp.repository.StudentActivitiesRepository;
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
public class StudentActivityController {
    @Autowired
    StudentActivitiesRepository studentActivitiesRepository;

    @GetMapping("/studentsactivities")
    public List<StudentActivitiesModel> getAllTeachersActivity() {
        return studentActivitiesRepository.findAll();
    }
    public StudentActivitiesModel findByUsername(String username) {
		return studentActivitiesRepository.findByFkusername(username);
    }
    @PostMapping("/studentsactivities")
    public StudentActivitiesModel createStudent(@Valid @RequestBody StudentActivitiesModel studentActivitiesModel) {
        StudentActivitiesModel us= findByUsername(studentActivitiesModel.getFkusername());
        if(us!=null){
            throw new SecurityException("Username is exists");
        }
        
        return studentActivitiesRepository.save(studentActivitiesModel);
    }

    @GetMapping("/studentsactivities/{username}")
    public StudentActivitiesModel getStudentById(@PathVariable(value = "username") String username) {
        return studentActivitiesRepository.findByFkusername(username);
               
    }

    @PutMapping("/studentsactivities/{username}")
    public StudentActivitiesModel updateStudent(@PathVariable(value = "username") String username,
                                           @Valid @RequestBody StudentActivitiesModel  studentDetailsModel) {

    StudentActivitiesModel teacher = studentActivitiesRepository.findByFkusername(username);
                // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        teacher.setCreatedAt(studentDetailsModel.getCreatedAt());
        teacher.setFkquestionformatid(studentDetailsModel.getFkquestionformatid());
        teacher.setVisibility(studentDetailsModel.getVisibility());
        teacher.setMarks(studentDetailsModel.getMarks());
        teacher.setUpdatedAt(studentDetailsModel.getUpdatedAt());
    
        StudentActivitiesModel teacherModel = studentActivitiesRepository.save(teacher);
        return teacherModel;
    }

    @DeleteMapping("/studentsactivities/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable(value = "id") String username) {
        StudentActivitiesModel teachers = studentActivitiesRepository.findByFkusername(username);
                // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

                studentActivitiesRepository.delete(teachers);

        return ResponseEntity.ok().build();
    }
}