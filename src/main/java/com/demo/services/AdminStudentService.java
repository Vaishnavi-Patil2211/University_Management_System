package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Student;
import com.demo.repositories.IStudentRepository;

@Service
public class AdminStudentService {
	
	@Autowired
	private IStudentRepository studentRepository;
	
    // Create a new student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    
    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
    
//    public Student updateStudent(Long studentId, Student updatedStudent) {
//        return studentRepository.findById(studentId).map(student -> {
//            student.setName(updatedStudent.getName());
//            student.setEmail(updatedStudent.getEmail());
//            student.setStudentProfile(updatedStudent.getStudentProfile());
//            return studentRepository.save(student);
//        }).orElseThrow(() -> new RuntimeException("Student not found"));
//    }
    
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


    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
    
    public Long getStudentCountByCourseCode(String courseCode) {
        return studentRepository.countStudentsByCourseCode(courseCode);
    }

}
