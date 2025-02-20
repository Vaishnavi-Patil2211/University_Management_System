package com.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.demo.entities.Assignment;
import com.demo.entities.Course;
import com.demo.entities.Student;
import com.demo.exceptions.CourseDeletionException;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.repositories.IAssignmentRepository;
import com.demo.repositories.ICourseRepository;
import com.demo.repositories.IStudentRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IStudentRepository studentRepository;
    
    @Autowired
    private IAssignmentRepository assignmentRepository;

    // Create a new course
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get course by ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Update a course
    public Course updateCourse(Long id, Course courseDetails) {
        return courseRepository.findById(id).map(course -> {
            course.setCourseCode(courseDetails.getCourseCode());
            course.setCourseName(courseDetails.getCourseName());
            course.setDescription(courseDetails.getDescription());
            course.setCreditHours(courseDetails.getCreditHours());
            return courseRepository.save(course);
        }).orElseThrow(() -> new RuntimeException("Course not found with id " + id));
    }

    // Delete a course
    public void deleteCourse(Long id) throws CourseDeletionException {
    	 try {
             courseRepository.deleteById(id);
         } catch (DataIntegrityViolationException e) {
             throw new CourseDeletionException("Cannot delete course with ID " + id + " because it is associated with existing students.");
         }
    }
    
    public List<Course> getCoursesByInstructorEmail(String email) {
        return courseRepository.findCoursesByInstructorEmail(email);
    }
    
    @Transactional
    public Course addAssignmentToCourse(Long courseId, Long assignmentId) throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + assignmentId));

        // Add assignment to course
        course.addAssignment(assignment);
        return courseRepository.save(course);
    }
    
    @Transactional
    public Course removeAssignmentFromCourse(Long courseId, Long assignmentId) throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + assignmentId));

        // Remove assignment from course
        course.removeAssignment(assignment);
        return courseRepository.save(course);
    }

    
}