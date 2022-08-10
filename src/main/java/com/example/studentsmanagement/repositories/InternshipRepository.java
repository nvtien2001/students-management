package com.example.studentsmanagement.repositories;

import com.example.studentsmanagement.entity.Internship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipRepository extends CrudRepository<Internship, Long> {
    @Query("SELECT i FROM Internship i")
    Page<Internship> findInterns(Pageable pageable);
}
