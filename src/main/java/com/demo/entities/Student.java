package com.demo.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message="name can not be blank")
    private String name;
	
	@NotBlank
	@Email(message="Invalid email format")
    private String email;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL,orphanRemoval = true)
    //@JsonManagedReference
    @JoinColumn(name = "student_id")
    private StudentProfile studentProfile;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
   // @JsonManagedReference
    private Set<Course> courses = new HashSet<>();

    // No-arg Constructors
    public Student() {
    	
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
      

    public Student(String name, String email, StudentProfile studentProfile) {
		super();
		this.name = name;
		this.email = email;
		this.studentProfile = studentProfile;
	}

	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
        studentProfile.setStudent(this);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
        course.getStudents().remove(this);
    }

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", studentProfile=" + studentProfile
				+ ", courses=" + courses + "]";
	}
    
    
}
