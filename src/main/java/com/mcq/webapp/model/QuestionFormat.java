package com.mcq.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;

 
@Entity
@Table(name = "questionformat")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class QuestionFormat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    private String name;

    @NotNull(message = "Please enter Question count")
    private int questionscount;

    @NotNull(message = "Please enter Subject Id")
    private int subjectid;

    @NotNull(message = "Please enter Lesson Id")
    private int lessonid;

    @NotNull(message = "Please enter userid")
    private int userid;

    @NotNull(message = "Please enter Time duration")
    private double timeduration;

    @NotNull(message = "Please enter Number of levels")
    private int numberoflevels;

    @NotNull(message = "Please enter Question Id")
    private int questionid;

    @NotNull(message = "Please enter Grade Id")
    private int gradeid;

    @NotNull(message = "Please enter Type Id")
    private int typeid;

    @NotNull(message = "Please enter Term Id")
    private int termid;
   

    private int visibility;


    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionscount() {
        return questionscount;
    }

    public void setQuestionscount(int questionscount) {
        this.questionscount = questionscount;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

    public int getLessonid() {
        return lessonid;
    }

    public void setLessonid(int lessonid) {
        this.lessonid = lessonid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getTimeduration() {
        return timeduration;
    }

    public void setTimeduration(double timeduration) {
        this.timeduration = timeduration;
    }

    public int getNumberoflevels() {
        return numberoflevels;
    }

    public void setNumberoflevels(int numberoflevels) {
        this.numberoflevels = numberoflevels;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public int getGradeid() {
        return gradeid;
    }

    public void setGradeid(int gradeid) {
        this.gradeid = gradeid;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getTermid() {
        return termid;
    }

    public void setTermid(int termid) {
        this.termid = termid;
    }

    
     
}
