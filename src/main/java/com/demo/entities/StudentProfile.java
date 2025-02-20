package com.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class StudentProfile {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message="Address should not be blank")
    private String address;
	
	@NotBlank
	@Size(min = 10, max = 10, message = "Length must be exactly 10 characters")
    private String phone;
	
	@NotBlank(message="major can not be blank")
    private String major;

    @OneToOne
    @JsonBackReference
    private Student student;

	public StudentProfile() {
		super();
	}


	public StudentProfile(String address, String phone, String major) {
		super();
		this.address = address;
		this.phone = phone;
		this.major = major;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "StudentProfile [id=" + id + ", address=" + address + ", phone=" + phone + ", major=" + major
				+ ", student=" + student + "]";
	}
    
    
}
