package com.mcq.webapp.controller;

import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Subject;
 
import com.mcq.webapp.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

 

@RestController
@RequestMapping("/api")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;


    @GetMapping("/subject")
    public List<Subject>getAllSubject() {
        
        return subjectRepository.findAll();
    }
    public Subject findBysubject(String subject) {
		return subjectRepository.findBySubject(subject);
    }
    @PostMapping("/subject")
    public Subject createsubject(@Valid @RequestBody Subject subject) {
        Subject us= findBySubject(subject.getSubject());
        if(us!=null){
            throw new SecurityException("term name is exists");
        }
        return subjectRepository.save(subject);
    }
    public Subject findBySubject(String subject) {
		return subjectRepository.findBySubject(subject);
    }

    @GetMapping("/subject/{id}")
    public Subject getSubjectById(@PathVariable(value = "id") Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectId));
    }

    @PutMapping("/subject/{id}")
    public Subject updatesubject(@PathVariable(value = "id") Long subjectid,
                                           @Valid @RequestBody Subject subjectDetails) {

        Subject subject = subjectRepository.findById(subjectid)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectid));

                subject.setSubject(subjectDetails.getSubject());
                subject.setCreatedAt(subjectDetails.getCreatedAt());
                subject.setVisibility(subjectDetails.getVisibility());
                subject.setDescription(subject.getDescription());
                subject.setFkgrade(subject.getFkgrade());
            

        Subject updatedlevel = subjectRepository.save(subject);
        return updatedlevel;
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<?> deletesubject(@PathVariable(value = "id") Long term) {
        Subject gra = subjectRepository.findById(term)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", term));

                subjectRepository.delete(gra);

        return ResponseEntity.ok().build();
    }
}
