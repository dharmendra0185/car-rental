package com.example.carrental.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PricingService {
	
	//Static pricing replace with DB if needed
	private static final Map<String, Double> RATES = Map.of(
			
			"SMALL", 29.99,
			"MEDIUM", 45.99,
			"LARGE", 59.99,
			"EXTRA LARGE", 79.99
			);
	public double findRatePerDay(String rawCategory)
	{
		if(rawCategory == null) throw new IllegalArgumentException("category is required");
		String category = rawCategory.trim().toUpperCase().replace(',', '_');
		Double rate = RATES.get(category);
		if(rate == null)
		{
			throw new IllegalArgumentException("Invalid car category Allowed: SMALL, MEDIUM, LARGE, EXTRA_LARGE ");
		}
		return rate;
	}

}
