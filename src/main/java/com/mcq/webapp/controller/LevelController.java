package com.mcq.webapp.controller;

import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Level;
import com.mcq.webapp.model.Type;

import com.mcq.webapp.repository.LevelRepository;
import com.mcq.webapp.repository.TypeRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

 

@RestController
@RequestMapping("/api")
public class LevelController {

    @Autowired
    LevelRepository levelRepository;


    @GetMapping("/level")
    public List<Level>getAllevel() {
        
        return levelRepository.findAll();
    }
    public Level findBylevel(String level) {
		return levelRepository.findBylevel(level);
    }
    @PostMapping("/level")
    public Level creaelevel(@Valid @RequestBody Level level) {
        Level us= findBylevel(level.getlevel());
        if(us!=null){
            throw new SecurityException("level name is exists");
        }
        return levelRepository.save(level);
    }

    @GetMapping("/level/{id}")
    public Level getlevelById(@PathVariable(value = "id") Long levelId) {
        return levelRepository.findById(levelId)
                .orElseThrow(() -> new ResourceNotFoundException("Level", "id", levelId));
    }

    @PutMapping("/level/{id}")
    public Level updatelevel(@PathVariable(value = "id") Long levelId,
                                           @Valid @RequestBody Level levelDetails) {

        Level level = levelRepository.findById(levelId)
                .orElseThrow(() -> new ResourceNotFoundException("Level", "id", levelId));

        level.setlevel(levelDetails.getlevel());
        level.setCreatedAt(levelDetails.getCreatedAt());
        level.setVisibility(levelDetails.getVisibility());


        Level updatedlevel = levelRepository.save(level);
        return updatedlevel;
    }

    @DeleteMapping("/level/{id}")
    public ResponseEntity<?> deletelevel(@PathVariable(value = "id") Long level) {
        Level gra = levelRepository.findById(level)
                .orElseThrow(() -> new ResourceNotFoundException("Level", "id", level));

        levelRepository.delete(gra);

        return ResponseEntity.ok().build();
    }
}
