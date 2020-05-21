package com.mcq.webapp.controller;

import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Term;
import com.mcq.webapp.model.Type;

import com.mcq.webapp.repository.LevelRepository;
import com.mcq.webapp.repository.TermRepository;
import com.mcq.webapp.repository.TypeRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

 

@RestController
@RequestMapping("/api")
public class TermController {

    @Autowired
    TermRepository termRepository;


    @GetMapping("/term")
    public List<Term>getAllTerm() {
        
        return termRepository.findAll();
    }
    public Term findByTerm(String term) {
		return termRepository.findByTerm(term);
    }
    @PostMapping("/term")
    public Term createterm(@Valid @RequestBody Term term) {
        Term us= findByTerm(term.getTerm());
        if(us!=null){
            throw new SecurityException("term name is exists");
        }
        return termRepository.save(term);
    }

    @GetMapping("/term/{id}")
    public Term gettermById(@PathVariable(value = "id") Long levelId) {
        return termRepository.findById(levelId)
                .orElseThrow(() -> new ResourceNotFoundException("Term", "id", levelId));
    }

    @PutMapping("/term/{id}")
    public Term updateterm(@PathVariable(value = "id") Long levelId,
                                           @Valid @RequestBody Term termDetails) {

        Term term = termRepository.findById(levelId)
                .orElseThrow(() -> new ResourceNotFoundException("Term", "id", levelId));

        term.setTerm(termDetails.getTerm());
        term.setCreatedAt(termDetails.getCreatedAt());
        term.setVisibility(termDetails.getVisibility());


        Term updatedlevel = termRepository.save(term);
        return updatedlevel;
    }

    @DeleteMapping("/term/{id}")
    public ResponseEntity<?> deletelevel(@PathVariable(value = "id") Long term) {
        Term gra = termRepository.findById(term)
                .orElseThrow(() -> new ResourceNotFoundException("Term", "id", term));

                termRepository.delete(gra);

        return ResponseEntity.ok().build();
    }
}
