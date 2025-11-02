package com.example.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carrental.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
