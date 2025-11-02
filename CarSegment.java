package com.example.carrental.segment;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CarSegment {
	SMALL, MEDIUM, LARGE, EXTRALARGE;
	
	@JsonCreator
	public static CarSegment fromJson(String value) throws IllegalAccessException
	{
		if(value == null) throw new IllegalArgumentException("carSegment is required");
		String norm = value.trim().replaceAll("\\s+", "").toUpperCase();
		return switch(norm) {
		case "SMALL" -> SMALL;
		case "MEDIUM" -> MEDIUM;
		case "LARGE" -> LARGE;
		case "EXTRALARGE" -> EXTRALARGE;
		default -> throw new IllegalAccessException("Invalid CarSegment, Allowed: Small, Medium, Large, Extra Large");
		};
		
	}

}
