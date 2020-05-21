package com.mcq.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

 
@Entity
@Table(name = "users",uniqueConstraints=
@UniqueConstraint(columnNames = {"username", "email_address","mobile_num1"}))
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Users {
    //@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    @Id
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String first_name;

    @NotBlank
    private String last_name;

    @NotBlank
    private String address_line1;

    @NotBlank
    private String address_line2;

    @NotBlank
    private String city;


    @NotBlank
    @Email
    @Column(unique=true)
    private String email_address;

    @NotBlank
    @Column(unique = true)
    private String mobile_num1;

     
    private int role;

    private String mobile_num2;

     

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }
    

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
    } private int visibility;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String firstname) {
        this.first_name = firstname;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String lastname) {
        this.last_name = lastname;
    }

    public String getAddress_line1() {
        return this.address_line1;
    }

    public void setAddress_line1(String addressline1) {
        this.address_line1 = addressline1;
    }

    public String getAddress_line2() {
        return this.address_line2;
    }

    public void setAddress_line2(String addressline2) {
        this.address_line2 = addressline2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile_num1() {
        return this.mobile_num1;
    }

    public void setMobile_num1(String mobileNum1) {
        this.mobile_num1 = mobileNum1;
    }

    public String getMobile_num2() {
        return this.mobile_num2;
    }

    public void setMobile_num2(String mobileNum2) {
        this.mobile_num2 = mobileNum2;
    }

    public int getRole() {
        return this.role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getVisibility() {
        return this.visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

   

}
