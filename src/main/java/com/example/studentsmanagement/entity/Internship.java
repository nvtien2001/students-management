package com.example.studentsmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Table(name = "internship")
@Getter
@Setter
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Email
    private String email;
    private String phone_number;
    private Long university_id;
    private int scholastic;
    private Long mentor_id;
    private String company_card_id;
    private Long position_id;
    private int level;
    private String status;
}
