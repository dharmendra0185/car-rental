package com.example.carrental.service;

import org.springframework.stereotype.Service;

import com.example.carrental.entity.DrivingLicense;
import com.example.carrental.exceptions.ResourceNotFoundException;
import com.example.carrental.repository.DrivingLicenseRepository;

import om.example.carrental.dto.LicenseDetailsResponse;

@Service
public class LicenseService {
	
	private final DrivingLicenseRepository licenseRepository;

	
	public LicenseService(DrivingLicenseRepository licenseRepository)
	{
		this.licenseRepository = licenseRepository;
	}
	
	public LicenseDetailsResponse getLicenseDetails(String licenseNumber)
	{
		DrivingLicense dl = licenseRepository.findByLicenseNumber(licenseNumber).orElseThrow(()
				-> new ResourceNotFoundException("Driving License not found: " + licenseNumber));
		return new LicenseDetailsResponse(dl.getOwnerName(), dl.getExpiryDate());
		
	}
}
