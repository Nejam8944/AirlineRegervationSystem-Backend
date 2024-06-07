package com.airlinereservationsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketBookingRequest {

	private String email;
    private String flightNumber;
    private String flightClass;
    private int totalPassengers;
}
