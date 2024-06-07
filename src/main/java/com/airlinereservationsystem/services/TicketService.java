package com.airlinereservationsystem.services;

import java.util.List;

import com.airlinereservationsystem.entity.Ticket;

public interface TicketService {
    Ticket bookTicket(String email, String flightNumber, String flightClass, int totalPassengers);
    void cancelTicket(String ticketId);
    List<Ticket> getAllTicketsByflightNumber(String flightNumber);
    List<Ticket> getAllConfirmTicketsByflightNumber(String flightNumber);
}
