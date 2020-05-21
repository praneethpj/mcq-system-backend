package com.mcq.webapp.controller;

import com.mcq.webapp.dao.UserDao;
import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Answers;
import com.mcq.webapp.model.Questions;
import com.mcq.webapp.model.Users;
import com.mcq.webapp.repository.AnswersRepository;
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
public class AnswersController {

   
    @Autowired
    private AnswersRepository answersRepository;
 
 

    @GetMapping("/getAllAnswers")
    public List<Answers> getAllAnswers() {
        return answersRepository.findAll();
    }
    
 
    @PostMapping("/answers")
    public Answers createAnswers(@Valid @RequestBody Answers answers) {
        return answersRepository.save(answers);
    }

    @GetMapping("/answers/{id}")
    public Answers AnswerById(@PathVariable(value = "id") Long answerId) {
        return answersRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer", "id", answerId));
    }

    @PutMapping("/answer/{id}")
    public Answers updateAnswer(@PathVariable(value = "id") Long answerId,
                                           @Valid @RequestBody Answers answerDetails) {

        Answers note = answersRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer", "id", answerId));

        note.setQuestionid(answerDetails.getQuestionid());
        note.setAnswertitle(answerDetails.getAnswertitle());
        note.setCorrectflag(answerDetails.getCorrectflag());
        note.setSingleormultiple(answerDetails.getSingleormultiple());
        note.setVisibility(answerDetails.getVisibility());

        Answers updatedNote = answersRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/answers/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable(value = "id") Long answerId) {
        Answers note = answersRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Answers", "id", answerId));

        answersRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
