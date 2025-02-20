package com.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.demo.entities.Course;
import com.demo.exceptions.CourseDeletionException;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.services.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/admin/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Create a new course
    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        Course savedCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id,@Valid @RequestBody Course courseDetails) {
        Course updatedCourse = courseService.updateCourse(id, courseDetails);
        return ResponseEntity.ok(updatedCourse);
    }

    // Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) throws CourseDeletionException {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/instructor")
    public ResponseEntity<List<Course>> getCoursesByInstructorEmail(@RequestParam String email) {
        return ResponseEntity.ok(courseService.getCoursesByInstructorEmail(email));
    }
    
    @PostMapping("/{courseId}/assignments/{assignmentId}")
    public ResponseEntity<Course> addAssignmentToCourse(
            @PathVariable Long courseId, 
            @PathVariable Long assignmentId) throws ResourceNotFoundException {
        
        Course updatedCourse = courseService.addAssignmentToCourse(courseId, assignmentId);
        return ResponseEntity.ok(updatedCourse);
    }
    
    @DeleteMapping("/{courseId}/assignments/{assignmentId}")
    public ResponseEntity<Course> removeAssignmentFromCourse(@PathVariable Long courseId, @PathVariable Long assignmentId) throws ResourceNotFoundException {
        Course updatedCourse = courseService.removeAssignmentFromCourse(courseId, assignmentId);
        return ResponseEntity.ok(updatedCourse);
    }

    
}