package com.example.studentsmanagement.repositories;

import com.example.studentsmanagement.entity.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {

}
