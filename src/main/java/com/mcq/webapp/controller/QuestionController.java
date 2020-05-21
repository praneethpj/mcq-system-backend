package com.mcq.webapp.controller;

import com.mcq.webapp.dao.UserDao;
import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Questions;
import com.mcq.webapp.model.Users;
import com.mcq.webapp.repository.QuestionsRepository;
import com.mcq.webapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;
 
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
 

@RestController
@RequestMapping("/api")
public class QuestionController {

   
    @Autowired
    private QuestionsRepository questionsRepository;
 
 

    @GetMapping("/getAllQuestions")
    public List<Questions> getAllQuestion() {
        return questionsRepository.findAll();
    }
    
 
    @PostMapping("/questions")
    public Questions createQuestion(@Valid @RequestBody Questions questions) {
        return questionsRepository.save(questions);
    }

    @GetMapping("/question/{id}")
    public Questions getQuestionById(@PathVariable(value = "id") Long questionId) {
        return questionsRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));
    }

    @PutMapping("/question/{id}")
    public Questions updateQuestion(@PathVariable(value = "id") Long questionId,
                                           @Valid @RequestBody Questions questionDetails) {

        Questions note = questionsRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));

        note.setLevelid(questionDetails.getLevelid());
        note.setDescription(questionDetails.getDescription());
        note.setMark(questionDetails.getMark());

        Questions updatedNote = questionsRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable(value = "id") Long questionId) {
        Questions note = questionsRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));

        questionsRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
