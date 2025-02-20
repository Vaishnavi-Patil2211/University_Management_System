package com.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.Assignment;

@Repository
public interface IAssignmentRepository extends JpaRepository<Assignment, Long>{

	List<Assignment> findByDueDateBefore(LocalDate dueDate);

}
