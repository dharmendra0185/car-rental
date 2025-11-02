package com.example.carrental.entity;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "driving_license")
public class DrivingLicense {
	
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private Long id;

@Column(unique = true, nullable = false)
private String licenseNumber;

@Column(nullable = false)
private String ownerName;

@Column(nullable = false)
private LocalDate expiryDate;


public LocalDate getExpiryDate() {
	return expiryDate;
}

public void setExpiryDate(LocalDate expiryDate) {
	this.expiryDate = expiryDate;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getLicenseNumber() {
	return licenseNumber;
}

public void setLicenseNumber(String licenseNumber) {
	this.licenseNumber = licenseNumber;
}

public String getOwnerName() {
	return ownerName;
}

public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
}


	
	

}
