package com.example.studentsmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "company_card")
@Getter
@Setter
public class CompanyCard {
    @Id
    private String id;
    private Long created_id;
    private Date created_date;
    private boolean used;
}
