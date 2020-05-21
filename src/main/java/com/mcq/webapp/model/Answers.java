package com.mcq.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

 
@Entity
@Table(name = "answers")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Answers {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    @NotBlank
    private String questionid;

    @NotBlank
    private String answertitle;


    @NotBlank
    private String correctflag;

    @NotBlank
    private String singleormultiple;

 
  
    private int visibility;
     

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    } 
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getAnswertitle() {
        return answertitle;
    }

    public void setAnswertitle(String answertitle) {
        this.answertitle = answertitle;
    }

    public String getCorrectflag() {
        return correctflag;
    }

    public void setCorrectflag(String correctflag) {
        this.correctflag = correctflag;
    }

    public String getSingleormultiple() {
        return singleormultiple;
    }

    public void setSingleormultiple(String singleormultiple) {
        this.singleormultiple = singleormultiple;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

     

   

}
