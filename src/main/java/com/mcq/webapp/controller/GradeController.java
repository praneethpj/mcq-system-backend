package com.mcq.webapp.controller;

import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Grade;
import com.mcq.webapp.model.Type;
import com.mcq.webapp.repository.GradeRepository;
import com.mcq.webapp.repository.TypeRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@RestController
@RequestMapping("/api")
public class GradeController {

    @Autowired
    GradeRepository gradeRepository;


    @GetMapping("/grade")
    public List<Grade>getAllGrade() {
        
        return gradeRepository.findAll();
    }
    public Grade findByGrade(String grade) {
		return gradeRepository.findByGrade(grade);
    }
    @PostMapping("/grade")
    public Grade creaeGrade(@Valid @RequestBody Grade grade) {
        Grade us= findByGrade(grade.getGrade());
        if(us!=null){
            throw new SecurityException("Grade name is exists");
        }
        return gradeRepository.save(grade);
    }

    @GetMapping("/grade/{id}")
    public Grade getGradeById(@PathVariable(value = "id") Long gradeId) {
        return gradeRepository.findById(gradeId)
                .orElseThrow(() -> new ResourceNotFoundException("Type", "id", gradeId));
    }

    @PutMapping("/grade/{id}")
    public Grade updateGrade(@PathVariable(value = "id") Long gradeId,
                                           @Valid @RequestBody Grade gradeDetails) {

        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new ResourceNotFoundException("Type", "id", gradeId));

        grade.setGrade(gradeDetails.getGrade());
        grade.setCreatedAt(gradeDetails.getCreatedAt());
        grade.setVisibility(gradeDetails.getVisibility());


        Grade updatedGrade = gradeRepository.save(grade);
        return updatedGrade;
    }

    @DeleteMapping("/grade/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable(value = "id") Long grade) {
        Grade gra = gradeRepository.findById(grade)
                .orElseThrow(() -> new ResourceNotFoundException("Type", "id", grade));

        gradeRepository.delete(gra);

        return ResponseEntity.ok().build();
    }
}
