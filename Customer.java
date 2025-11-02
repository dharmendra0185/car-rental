package com.example.carrental.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="customer")
public class Customer {
	@Id @GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String drivingLicenseNumber;
	
	private String name;
	private Integer age;
	
	@Column(nullable= false)
	private LocalDate licenseIssueDate;
	
	private LocalDate dateOfBirth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getLicenseIssueDate() {
		return licenseIssueDate;
	}

	public void setLicenseIssueDate(LocalDate licenseIssueDate) {
		this.licenseIssueDate = licenseIssueDate;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	

}
