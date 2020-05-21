package com.mcq.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

 
@Entity
@Table(name = "lesson",uniqueConstraints=
@UniqueConstraint(columnNames = {"lesson"}))
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String lesson;

 
    private String description;

   
    private int visibility=1;

     
    private int fkgrade;

   
    private int fktype;

    private int fkterm;

    private int fksubject;

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

    public String getLesson(){
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
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
 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getFkgrade() {
        return fkgrade;
    }

    public void setFkgrade(int fkgrade) {
        this.fkgrade = fkgrade;
    }

    public int getFktype() {
        return fktype;
    }

    public void setFktype(int fktype) {
        this.fktype = fktype;
    }

    public int getFkterm() {
        return fkterm;
    }

    public void setFkterm(int fkterm) {
        this.fkterm = fkterm;
    }

    public int getFksubject() {
        return fksubject;
    }

    public void setFksubject(int fksubject) {
        this.fksubject = fksubject;
    }

}
