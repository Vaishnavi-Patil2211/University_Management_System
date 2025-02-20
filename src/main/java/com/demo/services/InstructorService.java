package com.demo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Instructor;
import com.demo.repositories.IInstructorRepository;

@Service
public class InstructorService {

    @Autowired
    private IInstructorRepository instructorRepository;

    // Create a new instructor
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    // Get all instructors
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // Get instructor by ID
    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    // Update an instructor
    public Instructor updateInstructor(Long id, Instructor instructorDetails) {
        return instructorRepository.findById(id).map(instructor -> {
            instructor.setName(instructorDetails.getName());
            instructor.setEmail(instructorDetails.getEmail());
            instructor.setDepartment(instructorDetails.getDepartment());
            return instructorRepository.save(instructor);
        }).orElseThrow(() -> new RuntimeException("Instructor not found with id " + id));
    }

    // Delete an instructor
    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}

