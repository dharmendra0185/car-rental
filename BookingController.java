package com.example.carrental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carrental.service.BookingService;
import com.example.carrental.service.LicenseService;
import com.example.carrental.service.PricingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import om.example.carrental.dto.BookingConfirmRequest;
import om.example.carrental.dto.BookingConfirmResponse;
import om.example.carrental.dto.BookingResponse;
import om.example.carrental.dto.LicenseDetailsResponse;
import om.example.carrental.dto.RateRequest;
import om.example.carrental.dto.RateResponse;

@RestController
@RequestMapping("/host/car-rental-pricing-api")
@Tag(name = "Bookings", description = "Confirm car rental bookings")
public class BookingController {
	
	private final BookingService bookingservice;
	private final PricingService pricingService;
	private final LicenseService licenseService;
	
	public BookingController(BookingService bookingservice)
	{
		this.bookingservice = bookingservice;
		this.pricingService = new PricingService();
		this.licenseService = null;
	}

	@Operation(summary = "Confirm a car rental booking")
	@PostMapping("/confirm")
	public ResponseEntity<BookingConfirmResponse> confirm(@Valid @RequestBody BookingConfirmRequest request){
		BookingConfirmResponse response = bookingservice.confirm(request);
		return ResponseEntity.status(201).body(response);
		
	}
	
	@Operation(summary = "Get Booking details by ID")
	@GetMapping("/{bookingsId}")
	public ResponseEntity<BookingResponse> getById(@PathVariable Long bookingId){
		
		return ResponseEntity.ok(bookingservice.getBookingDetails(bookingId));
	}
	
	@Operation(summary = "Retrive Per Day rental rate")
	@PostMapping("/rental/rate")
	public ResponseEntity<?> rate(@Valid @RequestBody RateRequest request){
		double rate = pricingService.findRatePerDay(request.getCategory());
		return ResponseEntity.ok(new RateResponse(request.getCategory().trim().toUpperCase().replace(',', '_'), rate));
	}
	@Operation(summary = "Get License details by license number")
	@GetMapping("/license/details/{licenseNumber}")
	public ResponseEntity<LicenseDetailsResponse> getDetails(@PathVariable String licenseNumber){
		
		return ResponseEntity.ok(licenseService.getLicenseDetails(licenseNumber));
	}
	
	
}
