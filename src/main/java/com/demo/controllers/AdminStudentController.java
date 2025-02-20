package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entities.Student;
import com.demo.services.AdminStudentService;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/admin/students")

public class AdminStudentController {
	
	 @Autowired
	    private AdminStudentService adminStudentService;
	 
	    // Create a new student
	    @PostMapping//consumes = MediaType.APPLICATION_JSON_VALUE
	    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {//, @RequestHeader HttpHeaders headers
	    	//System.out.println("Request Headers: " + headers);
	        Student savedStudent = adminStudentService.createStudent(student);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent); 
	    }

	    @GetMapping
	    public ResponseEntity<List<Student>> getAllStudents() {
	        return ResponseEntity.ok(adminStudentService.getAllStudents());
	    }
	    
	    @GetMapping("/{studentId}")
	    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
	        return ResponseEntity.ok(adminStudentService.getStudentById(studentId));
	    }
	    
//	    @PutMapping("/{studentId}")
//	    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId,@Valid @RequestBody Student student) {
//	        return ResponseEntity.ok(adminStudentService.updateStudent(studentId, student));
//	    }
	    
	    @PutMapping("/{studentId}")
	    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @Valid @RequestBody Student student) {
	        return ResponseEntity.ok(adminStudentService.updateStudent(studentId, student));
	    }

	    @DeleteMapping("/{studentId}")
	    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
	        adminStudentService.deleteStudent(studentId);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @GetMapping("/count-by-course")
	    public ResponseEntity<Long> getStudentCountByCourse(@RequestParam String courseCode) {
	        return ResponseEntity.ok(adminStudentService.getStudentCountByCourseCode(courseCode));
	    }

}
