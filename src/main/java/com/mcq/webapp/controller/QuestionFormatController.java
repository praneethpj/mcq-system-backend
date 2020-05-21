package com.mcq.webapp.controller;

import com.mcq.webapp.dao.UserDao;
import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Answers;
import com.mcq.webapp.model.QuestionFormat;
import com.mcq.webapp.model.Questions;
import com.mcq.webapp.model.Users;
import com.mcq.webapp.repository.QuestionsFormatRepository;
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
public class QuestionFormatController {

   
    @Autowired
    private QuestionsFormatRepository questionsFormatRepository;
 
 

    @GetMapping("/getAllQuestionsFormat")
    public List<QuestionFormat> getAllQuestionFormat() {
        return questionsFormatRepository.findAll();
    }
    
 
    @PostMapping("/questionsformat")
    public QuestionFormat createQuestionFormat(@Valid @RequestBody QuestionFormat questionformt) {
        return questionsFormatRepository.save(questionformt);
    }

    @GetMapping("/questionformat/{id}")
    public QuestionFormat getQuestionFormatById(@PathVariable(value = "id") Long questionFormatId) {
        return questionsFormatRepository.findById(questionFormatId)
                .orElseThrow(() -> new ResourceNotFoundException("Questionformat", "id", questionFormatId));
    }

    @PutMapping("/questionformat/{id}")
    public QuestionFormat updateFormat(@PathVariable(value = "id") Long questionFormatId,
                                           @Valid @RequestBody QuestionFormat questionFormatDetails) {

        QuestionFormat note = questionsFormatRepository.findById(questionFormatId)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionFormat", "id", questionFormatId));

        note.setLessonid(questionFormatDetails.getLessonid());
        note.setName(questionFormatDetails.getName());
        note.setQuestionscount(questionFormatDetails.getQuestionscount());
        note.setSubjectid(questionFormatDetails.getSubjectid());
        note.setUserid(questionFormatDetails.getUserid());
        note.setGradeid(questionFormatDetails.getGradeid());
        note.setNumberoflevels(questionFormatDetails.getNumberoflevels());
        note.setQuestionid(questionFormatDetails.getQuestionid());
        note.setTermid(questionFormatDetails.getTermid());
        note.setTimeduration(questionFormatDetails.getTimeduration());
        note.setTypeid(questionFormatDetails.getTypeid());
        note.setVisibility(questionFormatDetails.getVisibility());

        QuestionFormat updatedNote = questionsFormatRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/questionformat/{id}")
    public ResponseEntity<?> deleteFormatQuestion(@PathVariable(value = "id") Long questionformatId) {
        QuestionFormat note = questionsFormatRepository.findById(questionformatId)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionFormat", "id", questionformatId));

                questionsFormatRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
