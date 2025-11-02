package com.example.carrental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carrental.entity.DrivingLicense;

public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Long>{

	Optional<DrivingLicense> findByLicenseNumber(String licenseNumber);
}
