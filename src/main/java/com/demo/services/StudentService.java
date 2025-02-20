package com.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Assignment;
import com.demo.entities.Course;
import com.demo.entities.Student;
import com.demo.repositories.ICourseRepository;
import com.demo.repositories.IStudentRepository;

@Service
public class StudentService {
	@Autowired
    private IStudentRepository studentRepository;
	
	 @Autowired
	    private ICourseRepository courseRepository;

	 
	    // Get student by ID
	    public Student getStudentById(Long studentId) {
	        return studentRepository.findById(studentId)
	                .orElseThrow(() -> new RuntimeException("Student not found"));
	    }

//	    // Update student details
//	    public Student updateStudent(Long id, Student studentDetails) {
//	        return studentRepository.findById(id).map(student -> {
//	            student.setName(studentDetails.getName());
//	            student.setEmail(studentDetails.getEmail());
//	            student.setStudentProfile(studentDetails.getStudentProfile());
//	            return studentRepository.save(student);
//	        }).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
//	    }
	    
	    public Student updateStudent(Long studentId, Student updatedStudent) {
	        return studentRepository.findById(studentId).map(student -> {
	            student.setName(updatedStudent.getName());
	            student.setEmail(updatedStudent.getEmail());

	            // Check if student profile exists
	            if (student.getStudentProfile() != null && updatedStudent.getStudentProfile() != null) {
	                student.getStudentProfile().setMajor(updatedStudent.getStudentProfile().getMajor());
	            } else if (updatedStudent.getStudentProfile() != null) {
	                // If there's no existing profile but an updated one is provided, set it
	                student.setStudentProfile(updatedStudent.getStudentProfile());
	            }

	            return studentRepository.save(student);
	        }).orElseThrow(() -> new RuntimeException("Student not found"));
	    }

	    // Delete student by ID
	    public void deleteStudent(Long id) {
	        studentRepository.deleteById(id);
	    }
	    
	    //Get All Enrolled Courses By Student ID
	    public Set<Course> getEnrolledCourses(Long studentId) {
	        Student student = getStudentById(studentId);
	        return student.getCourses();
	    }

	    //Enroll Student in A Course
	    public Student enrollStudentInCourse(Long studentId, Long courseId) {
	        Student student = getStudentById(studentId);
	        Course course = courseRepository.findById(courseId)
	                .orElseThrow(() -> new RuntimeException("Course not found"));

	        student.addCourse(course);
	        return studentRepository.save(student);
	    }

	    //Withdraw Student From A Course
	    public Student withdrawStudentFromCourse(Long studentId, Long courseId) {
	        Student student = getStudentById(studentId);
	        Course course = courseRepository.findById(courseId)
	                .orElseThrow(() -> new RuntimeException("Course not found"));

	        student.removeCourse(course);
	        return studentRepository.save(student);
	    }
	    
	    //Get Assignments By Student Id
	    public Set<Assignment> getStudentAssignments(Long studentId) {
	        Student student = studentRepository.findById(studentId)
	                .orElseThrow(() -> new RuntimeException("Student not found"));

	        Set<Assignment> assignments = new HashSet<>();
	        for (Course course : student.getCourses()) {
	            assignments.addAll(course.getAssignments());
	        }

	        return assignments;
	    }
	    
	    
}
