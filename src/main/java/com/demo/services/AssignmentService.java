package com.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Assignment;
import com.demo.repositories.IAssignmentRepository;

@Service
public class AssignmentService {

    @Autowired
    private IAssignmentRepository assignmentRepository;

    // Create a new assignment
    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    // Get all assignments
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    // Get assignment by ID
    public Optional<Assignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    // Update an assignment
    public Assignment updateAssignment(Long id, Assignment assignmentDetails) {
        return assignmentRepository.findById(id).map(assignment -> {
            assignment.setTitle(assignmentDetails.getTitle());
            assignment.setDueDate(assignmentDetails.getDueDate());
            assignment.setMaxScore(assignmentDetails.getMaxScore());
            return assignmentRepository.save(assignment);
        }).orElseThrow(() -> new RuntimeException("Assignment not found with id " + id));
    }

    // Delete an assignment
    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
    
    public List<Assignment> getAssignmentsDueBefore(LocalDate dueDate) {
        return assignmentRepository.findByDueDateBefore(dueDate);
    }
}