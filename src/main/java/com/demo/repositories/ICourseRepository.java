package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Course;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long>{
	
	@Query("SELECT c FROM Course c WHERE c.instructor.email = :email")
    List<Course> findCoursesByInstructorEmail(@Param("email") String email);

}
