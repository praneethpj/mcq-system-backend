package com.mcq.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

 
@Entity
@Table(name = "teacheractivities")//,uniqueConstraints=
//@UniqueConstraint(columnNames = {"teacher"}))
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class TeacherActivitiesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank //@Column(unique = true)
    private String fkusername;

    private int visibility=1;

    private int fkquestionid;

    private int fkquestionformatid;

    
 
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
 
 
    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getFkusername() {
        return fkusername;
    }

    public void setFkusername(String fkusername) {
        this.fkusername = fkusername;
    }

    public int getFkquestionid() {
        return fkquestionid;
    }

    public void setFkquestionid(int fkquestionid) {
        this.fkquestionid = fkquestionid;
    }

    public int getFkquestionformatid() {
        return fkquestionformatid;
    }

    public void setFkquestionformatid(int fkquestionformatid) {
        this.fkquestionformatid = fkquestionformatid;
    }
 
     

     

}
