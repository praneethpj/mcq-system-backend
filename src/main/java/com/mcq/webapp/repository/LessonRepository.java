package com.mcq.webapp.repository;

import com.mcq.webapp.model.Lesson;
import com.mcq.webapp.model.Subject;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Lesson findByLesson(String lesson);
}