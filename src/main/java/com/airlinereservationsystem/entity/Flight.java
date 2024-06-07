package com.airlinereservationsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight {

	@Id
	private String flightNumber;
	private String airplane;	
	private String departureAirport;	
	private String arrivalAirport;	
	private String flightStatus;	
	private LocalDateTime departureTime;	
	private LocalDateTime arrivalTime;
	private double economySeatFare;	
	private double businessSeatFare;	
	private double firstClassSeatFare;
	private int availableSeatOfEconomyClass;
	private int availableSeatOfBusinessClass;
	private int availableSeatOfFirstClass;
	public int getAvailableSeatsForClass(String flightClass) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void reduceAvailableSeatForClass(String flightClass) {
		// TODO Auto-generated method stub
		
	}
}
