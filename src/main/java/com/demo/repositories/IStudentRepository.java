package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Student;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
	
	@Query("SELECT COUNT(s) FROM Student s JOIN s.courses c WHERE c.courseCode = :courseCode")
    Long countStudentsByCourseCode(@Param("courseCode") String courseCode);

}
