package com.example.studentsmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mentor")
@Getter
@Setter
public class Mentor {
    @Id
    private Long id;
    private Long user_id;
    private int max_internship;
    private String card_photo;
}
