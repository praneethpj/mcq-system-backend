package com.mcq.webapp.controller;

import com.mcq.webapp.exception.ResourceNotFoundException;
import com.mcq.webapp.model.Lesson;
import com.mcq.webapp.model.Subject;
import com.mcq.webapp.repository.LessonRepository;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

 

@RestController
@RequestMapping("/api")
public class LessonController {

    @Autowired
    LessonRepository lessonRepository;


    @GetMapping("/lesson")
    public List<Lesson>getAllSubject() {
        
        return lessonRepository.findAll();
    }
    public Lesson findByLesson(String lesson) {
		return lessonRepository.findByLesson(lesson);
    }
    @PostMapping("/lesson")
    public Lesson createlesson(@Valid @RequestBody Lesson lesson) {
        Lesson us= findByLesson(lesson.getLesson());
        if(us!=null){
            throw new SecurityException("lesson name is exists");
        }
        return lessonRepository.save(lesson);
    }
 

    @GetMapping("/lesson/{id}")
    public Lesson getLessonById(@PathVariable(value = "id") Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("lesson", "id", lessonId));
    }

    @PutMapping("/lesson/{id}")
    public Lesson updatelesson(@PathVariable(value = "id") Long lessonid,
                                           @Valid @RequestBody Subject lessonDetails) {

        Lesson lesson = lessonRepository.findById(lessonid)
                .orElseThrow(() -> new ResourceNotFoundException("lesson", "id", lessonid));

                lesson.setLesson(lesson.getLesson());
                lesson.setCreatedAt(lesson.getCreatedAt());
                lesson.setVisibility(lesson.getVisibility());
                lesson.setDescription(lesson.getDescription());
                lesson.setFkgrade(lesson.getFkgrade());
                lesson.setFksubject(lesson.getFksubject());
                lesson.setFkterm(lesson.getFkterm());
                


        Lesson less = lessonRepository.save(lesson);
        return less;
    }

    @DeleteMapping("/lesson/{id}")
    public ResponseEntity<?> deletesubject(@PathVariable(value = "id") Long lesson) {
        Lesson gra = lessonRepository.findById(lesson)
                .orElseThrow(() -> new ResourceNotFoundException("lesson", "id", lesson));

                lessonRepository.delete(gra);

        return ResponseEntity.ok().build();
    }
}
