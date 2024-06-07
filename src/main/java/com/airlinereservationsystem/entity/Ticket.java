package com.airlinereservationsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {

    @Id
    private String ticketId;
    @ManyToOne
    private Flight flight;
    private String flightClass;
    private double seatFare;
    private String seatNumber;
    private LocalDateTime bookingTime;
    private String status;
    @ManyToOne
    private UserCredential user;
}
