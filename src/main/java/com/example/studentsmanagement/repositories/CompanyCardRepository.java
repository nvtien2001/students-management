package com.example.studentsmanagement.repositories;

import com.example.studentsmanagement.entity.CompanyCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCardRepository extends CrudRepository<CompanyCard, String> {
}
