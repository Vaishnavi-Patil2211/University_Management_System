package com.demo.entities;

import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Course {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message="course code can not be blank")
    private String courseCode;
	
	@NotBlank(message="course name can not be blank")
    private String courseName;
	
    private String description;
    
    @NotNull(message="credit hours can not be null")
    @Min(1)
    private int creditHours;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany(mappedBy = "courses")
    @JsonBackReference
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Assignment> assignments = new HashSet<>();

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
        assignment.setCourse(this);
    }

    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
        assignment.setCourse(null);
    }
    
	public Set<Student> getStudents() {
		return students;
	}

	public String getCourseCode() {
	    return courseCode;
	}

	public void setCourseCode(String courseCode) {
	    this.courseCode = courseCode;
	}

	public String getCourseName() {
	    return courseName;
	}

	public void setCourseName(String courseName) {
	    this.courseName = courseName;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public int getCreditHours() {
	    return creditHours;
	}

	public void setCreditHours(int creditHours) {
	    this.creditHours = creditHours;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Set<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseCode=" + courseCode + ", courseName=" + courseName + ", description="
				+ description + ", creditHours=" + creditHours + ", instructor=" + instructor + ", students=" + students
				+ ", assignments=" + assignments + "]";
	}
	
	

}
