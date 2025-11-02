package com.example.car_rental;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.carrental.segment.CarSegment;
import com.fasterxml.jackson.databind.ObjectMapper;

import om.example.carrental.dto.BookingConfirmRequest;

@SpringBootTest
@AutoConfigureMockMvc
class CarRentalApplicationTests {
	
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	
	@Test
	void confirmedBooking_OK() throws Exception{
		BookingConfirmRequest req = new BookingConfirmRequest();
		req.setDrivingLicenseNumber("DL-042019-7781");
		req.setAge(34);
		req.setReservationStartDate(LocalDate.now().plusDays(3));
		req.setReservationEndDate(LocalDate.now().plusDays(10));
		req.setCarSegment(CarSegment.MEDIUM);
		
		mockMvc.perform(post("/host/car-rental-pricing-api/confirm")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req)))
		        .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingID", notNullValue()));
	}
	@Test
	void confirmedBooking_FailsWhenMoreThen30Days() throws Exception{
		BookingConfirmRequest req = new BookingConfirmRequest();
		req.setDrivingLicenseNumber("DL-042019-7781");
		req.setAge(34);
		req.setReservationStartDate(LocalDate.now().plusDays(1));
		req.setReservationEndDate(LocalDate.now().plusDays(40));
		req.setCarSegment(CarSegment.LARGE);
		
		mockMvc.perform(post("/host/car-rental-pricing-api/confirm")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req)))
		        .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("cannot bew reserved for more then 30 days")));
	}
	@Test
	void confirmedBooking_FailsWhenMoreCustomerNotFound() throws Exception{
		BookingConfirmRequest req = new BookingConfirmRequest();
		req.setDrivingLicenseNumber("UNKNOWN-LIC");
		req.setAge(30);
		req.setReservationStartDate(LocalDate.now().plusDays(5));
		req.setReservationEndDate(LocalDate.now().plusDays(7));
		req.setCarSegment(CarSegment.EXTRALARGE);
		
		mockMvc.perform(post("/host/car-rental-pricing-api/confirm")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(req)))
		        .andExpect(status().isNotFound());
               
	}
}
