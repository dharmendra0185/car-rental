package com.example.carrental.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.carrental.segment.CarSegment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="booking")
public class Booking {

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getReservationStartDate() {
		return reservationStartDate;
	}
	public void setReservationStartDate(LocalDate reservationStartDate) {
		this.reservationStartDate = reservationStartDate;
	}
	
	public LocalDate getReservationEndDate() {
		return reservationEndDate;
	}
	public void setReservationEndDate(LocalDate reservationEndDate) {
		this.reservationEndDate = reservationEndDate;
	}
	public CarSegment getCarSegment() {
		return carSegment;
	}
	public void setCarSegment(CarSegment carSegment) {
		this.carSegment = carSegment;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public BigDecimal getRentalPrice() {
		return rentalPrice;
	}
	public void setRentalPrice(BigDecimal rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate reservationStartDate;
	
	@Column(nullable = false)
	private LocalDate reservationEndDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CarSegment carSegment;
	
	private Integer age;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	private BigDecimal rentalPrice;
	
	
	
}
