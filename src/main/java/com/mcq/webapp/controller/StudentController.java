package com.mcq.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import com.mcq.webapp.model.Note;
import com.mcq.webapp.model.StudentDetailsModel;
import com.mcq.webapp.model.TeacherDetailsModel;
import com.mcq.webapp.repository.StudentDetailsRepository;
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
public class StudentController {

    @Autowired
    StudentDetailsRepository studentDetailsRepository;

    @GetMapping("/student")
    public List<StudentDetailsModel> getAllStudent() {
        return studentDetailsRepository.findAll();
    }
    private StudentDetailsModel findByUsername(String username) {
		return studentDetailsRepository.findByFkusername(username);
    }
    @PostMapping("/student")
    public StudentDetailsModel createStudent(@Valid @RequestBody StudentDetailsModel studentDetailsmodel) {
        StudentDetailsModel us= findByUsername(studentDetailsmodel.getFkusername());
        if(us!=null){
            throw new SecurityException("Username is exists");
        }
        
        return studentDetailsRepository.save(studentDetailsmodel);
    }

    @GetMapping("/student/{username}")
    public StudentDetailsModel getStudentByUsername(@PathVariable(value = "username") String username) {
        return studentDetailsRepository.findByFkusername(username);
               
    }

    @PutMapping("/student/{username}")
    public StudentDetailsModel updateStudent(@PathVariable(value = "username") String username,
                                           @Valid @RequestBody StudentDetailsModel  studentDetailsModel) {

        StudentDetailsModel student = studentDetailsRepository.findByFkusername(username);
                // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        student.setAddressline1(studentDetailsModel.getAddressline1());
        student.setAddressline2(studentDetailsModel.getAddressline2());
        student.setCity(studentDetailsModel.getCity());
        student.setCreatedAt(studentDetailsModel.getCreatedAt());
        student.setDistrict(studentDetailsModel.getDistrict());
        student.setSchool(studentDetailsModel.geSchool());

        StudentDetailsModel studentModel = studentDetailsRepository.save(student);
        return studentModel;
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") String username) {
        StudentDetailsModel teachers = studentDetailsRepository.findByFkusername(username);
                // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        studentDetailsRepository.delete(teachers);

        return ResponseEntity.ok().build();
    }
}