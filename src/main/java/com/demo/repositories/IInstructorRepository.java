package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Instructor;

@Repository
public interface IInstructorRepository extends JpaRepository<Instructor, Long> {

}
