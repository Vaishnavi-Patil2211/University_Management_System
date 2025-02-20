package com.demo.controllers;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entities.Assignment;
import com.demo.entities.Course;
import com.demo.entities.Student;
import com.demo.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/students")
public class StudentController {


	    @Autowired
	    private StudentService studentService;

	    // Get a student by ID
	    @GetMapping("/{studentId}")
	    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
	        return ResponseEntity.ok(studentService.getStudentById(studentId));
	    }


	    // Update a student's details
	    @PutMapping("/{id}")
	    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@Valid  @RequestBody Student studentDetails) {
	        Student updatedStudent = studentService.updateStudent(id, studentDetails);
	        return ResponseEntity.ok(updatedStudent);
	    }

	    // Delete a student
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
	        studentService.deleteStudent(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	 // Get enrolled courses for a student
	    @GetMapping("/{studentId}/courses")
	    public ResponseEntity<Set<Course>> getEnrolledCourses(@PathVariable Long studentId) {
	        Set<Course> courses = studentService.getEnrolledCourses(studentId);
	        return ResponseEntity.ok(courses);
	    }

	    // Enroll a student in a course
	    @PostMapping("/{studentId}/courses/{courseId}")
	    public ResponseEntity<Student> enrollStudentInCourse(
	            @PathVariable Long studentId,
	            @PathVariable Long courseId) {
	        Student updatedStudent = studentService.enrollStudentInCourse(studentId, courseId);
	        return ResponseEntity.ok(updatedStudent);
	    }

	    // Withdraw a student from a course
	    @DeleteMapping("/{studentId}/courses/{courseId}")
	    public ResponseEntity<Student> withdrawStudentFromCourse(
	            @PathVariable Long studentId,
	            @PathVariable Long courseId) {
	        Student updatedStudent = studentService.withdrawStudentFromCourse(studentId, courseId);
	        return ResponseEntity.ok(updatedStudent);
	    }
	    
	 // Get assignments for the courses a student is enrolled in
	    @GetMapping("/{studentId}/assignments")
	    public ResponseEntity<Set<Assignment>> getStudentAssignments(@PathVariable Long studentId) {
	        Set<Assignment> assignments = studentService.getStudentAssignments(studentId);
	        return ResponseEntity.ok(assignments);
	    }
	    
	    
	
}
