package com.mcq.webapp.controller;

import com.mcq.webapp.dao.UserDao;
import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Answers;
import com.mcq.webapp.model.QuestionFormat;
import com.mcq.webapp.model.QuestionFormatLevel;
import com.mcq.webapp.model.Questions;
import com.mcq.webapp.model.Users;
import com.mcq.webapp.repository.QuestionFormatLevelRepository;
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
public class QuestionFormatLevelController {

   
    @Autowired
    private QuestionFormatLevelRepository  questionsFormatLevelRepository;
 
 

    @GetMapping("/getAllQuestionsFormatLevel")
    public List<QuestionFormatLevel> getAllQuestionFormatLevel() {
        return questionsFormatLevelRepository.findAll();
    }
    
 
    @PostMapping("/questionsformatlevel")
    public QuestionFormatLevel createQuestionFormatLevel(@Valid @RequestBody QuestionFormatLevel questionformatlevel) {
        return questionsFormatLevelRepository.save(questionformatlevel);
    }

    @GetMapping("/questionformatlevel/{id}")
    public QuestionFormatLevel getQuestionFormatByIdLevel(@PathVariable(value = "id") Long questionFormatlevelId) {
        return questionsFormatLevelRepository.findById(questionFormatlevelId)
                .orElseThrow(() -> new ResourceNotFoundException("Questionformatlevel", "id", questionFormatlevelId));
    }

    @PutMapping("/questionformatlevel/{id}")
    public QuestionFormatLevel updateFormat(@PathVariable(value = "id") Long questionFormatId,
                                           @Valid @RequestBody QuestionFormatLevel questionFormatLevel) {

        QuestionFormatLevel note = questionsFormatLevelRepository.findById(questionFormatId)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionFormatLevel", "id", questionFormatId));

        note.setCount(questionFormatLevel.getCount());
        note.setLevelid(questionFormatLevel.getLevelid());
        note.setQuestionformatid(questionFormatLevel.getQuestionformatid());
         
        QuestionFormatLevel updatedNote = questionsFormatLevelRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/questionformatlevel/{id}")
    public ResponseEntity<?> deleteFormatQuestion(@PathVariable(value = "id") Long questionformatId) {
        QuestionFormatLevel note = questionsFormatLevelRepository.findById(questionformatId)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionFormatLevel", "id", questionformatId));

                questionsFormatLevelRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
