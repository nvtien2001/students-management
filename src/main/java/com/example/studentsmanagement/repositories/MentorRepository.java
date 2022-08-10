package com.example.studentsmanagement.repositories;

import com.example.studentsmanagement.entity.Mentor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends CrudRepository<Mentor, Long> {
}
