package com.example.studentsmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "review")
@Getter
@Setter
public class Review {
    @Id
    private Long id;
    private Long review_id;
    private String content;
}
