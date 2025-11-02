package com.example.carrental.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.carrental.entity.Booking;
import com.example.carrental.entity.Customer;
import com.example.carrental.exceptions.BusinessValidationException;
import com.example.carrental.exceptions.ResourceNotFoundException;
import com.example.carrental.repository.BookingRepository;
import com.example.carrental.repository.CustomerRepository;
import com.example.carrental.segment.CarSegment;

import om.example.carrental.dto.BookingConfirmRequest;
import om.example.carrental.dto.BookingConfirmResponse;
import om.example.carrental.dto.BookingResponse;

@Service
public class BookingService {
	private final BookingRepository bookingRepsository;
	private final CustomerRepository customerRepository;
	
	public BookingService(BookingRepository bookingRepsository, CustomerRepository customerRepository)
	{
		this.bookingRepsository = bookingRepsository;
		this.customerRepository = customerRepository;
	}
	
	public BookingConfirmResponse confirm(BookingConfirmRequest req) {
		LocalDate start = req.getReservationStartDate();
		LocalDate end = req.getReservationEndDate();
		if(end.isBefore(start))
		{
			throw new BusinessValidationException("Reservation end date must be on or after the start date");
		}
		long dayInclusive = ChronoUnit.DAYS.between(start, end)+ 1; // inclusive of both start and end
		if(dayInclusive > 30)
		{
			throw new BusinessValidationException("A car cannot be reserved for more then 30 days");
		}
		
		
		
		 Customer customer = customerRepository.findByDrivingLicenseNumber(req.getDrivingLicenseNumber()).orElseThrow(() 
				 -> new ResourceNotFoundException("Customer not found for driving license:"+ req.getDrivingLicenseNumber()));
		 
		 if(customer.getLicenseIssueDate() == null) {
			 throw new BusinessValidationException("Driving License issue date is not recorded");
		 }
		 
		 LocalDate oneYearBeforeStart = start.minusYears(1);
		 if(customer.getLicenseIssueDate().isAfter(oneYearBeforeStart))
		 {
			 throw new BusinessValidationException("The driving license must be at least 1 year old");
		 }
		  Booking booking = new Booking();
		  booking.setCustomer(customer);
		  booking.setAge(req.getAge());
		  booking.setReservationStartDate(start);
		  booking.setReservationEndDate(end);
		  
		  booking = bookingRepsository.save(booking);
		  
		  return new BookingConfirmResponse();
		}
	
	public BookingResponse getBookingDetails(Long bookingId)
	{
		Booking booking = bookingRepsository.findById(bookingId).orElseThrow(() 
				-> new ResourceNotFoundException("Booking with id" + bookingId + "not found"));
		
		Customer c = booking.getCustomer();
		BookingResponse resp = new BookingResponse();
		resp.setDrivingLicenseNumber(c.getDrivingLicenseNumber());
		resp.setCustomerName(c.getName());
		resp.setAge(c.getDateOfBirth() != null ? Period.between(c.getDateOfBirth(), LocalDate.now()).getYears() : 0);
		resp.setReservationStartDate(booking.getReservationStartDate());
		resp.setReservationEndDate(booking.getReservationEndDate());
		resp.setCarSegment(humanizeSegment(booking.getCarSegment()));
		resp.setRentalPrice(booking.getRentalPrice());
		return resp;
		
	}
	private String humanizeSegment(CarSegment seg)
	{
		return switch(seg)
				{
		case SMALL -> "Small";
		case MEDIUM -> "Medium";
		case LARGE -> "Large";
		case EXTRALARGE -> "Extra Large";
		};
	}

}
