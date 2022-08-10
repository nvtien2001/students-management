package com.example.studentsmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private String full_name;
    private String phone_number;
    private String role;
}
