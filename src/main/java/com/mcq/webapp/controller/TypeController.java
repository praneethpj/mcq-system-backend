package com.mcq.webapp.controller;

import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Type;
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
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

   

    @GetMapping("/types")
    public List<Type> getAllNotes() {
        
        return typeRepository.findAll();
    }

    @PostMapping("/types")
    public Type createNote(@Valid @RequestBody Type type) {
        return typeRepository.save(type);
    }

    @GetMapping("/types/{id}")
    public Type getNoteById(@PathVariable(value = "id") Long typeId) {
        return typeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Type", "id", typeId));
    }

    @PutMapping("/types/{id}")
    public Type updateType(@PathVariable(value = "id") Long typeId,
                                           @Valid @RequestBody Type TypeDetails) {

        Type type = typeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Type", "id", typeId));

        type.setType(TypeDetails.getType());
        type.setCreatedAt(TypeDetails.getCreatedAt());

        Type updatedType = typeRepository.save(type);
        return updatedType;
    }

    @DeleteMapping("/types/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long typeId) {
        Type note = typeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("Type", "id", typeId));

        typeRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
