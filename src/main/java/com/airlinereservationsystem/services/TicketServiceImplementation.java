package com.airlinereservationsystem.services;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airlinereservationsystem.entity.Flight;
import com.airlinereservationsystem.entity.Ticket;
import com.airlinereservationsystem.entity.UserCredential;
import com.airlinereservationsystem.repository.FlightRepository;
import com.airlinereservationsystem.repository.TicketRepository;
import com.airlinereservationsystem.repository.UserRepository;

@Service
public class TicketServiceImplementation implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Ticket bookTicket(String email, String flightNumber, String flightClass, int totalPassengers) {
        // Find the flight
        Flight flight = flightRepository.findById(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        // Find the user
        UserCredential user = userRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check seat availability and update
        int availableSeats;
        double seatFare;
        String seatPrefix;

        switch (flightClass) {
            case "Economy":
                availableSeats = flight.getAvailableSeatOfEconomyClass();
                seatFare = flight.getEconomySeatFare();
                seatPrefix = "E-";
                break;
            case "Business":
                availableSeats = flight.getAvailableSeatOfBusinessClass();
                seatFare = flight.getBusinessSeatFare();
                seatPrefix = "B-";
                break;
            case "First Class":
                availableSeats = flight.getAvailableSeatOfFirstClass();
                seatFare = flight.getFirstClassSeatFare();
                seatPrefix = "F-";
                break;
            default:
                throw new RuntimeException("Invalid flight class");
        }

        if (availableSeats < totalPassengers) {
            // Add to waiting list if no available seats
            for (int i = 0; i < totalPassengers; i++) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(UUID.randomUUID().toString());
                ticket.setFlight(flight);
                ticket.setFlightClass(flightClass);
                ticket.setSeatFare(seatFare);
                ticket.setSeatNumber("WL-" + (ticketRepository.countWaitingListTickets(flight, flightClass) + 1));
                ticket.setBookingTime(LocalDateTime.now());
                ticket.setStatus("Waiting");
                ticket.setUser(user);

                ticketRepository.save(ticket);
            }
            throw new RuntimeException("All seats are booked. Added to waiting list.");
        }

        // Deduct balance from user
        double totalFare = seatFare * totalPassengers;
        if (user.getTotalBalance() < totalFare) {
            throw new RuntimeException("Insufficient balance");
        }
        user.setTotalBalance(user.getTotalBalance() - totalFare);

        // Update repositories
        userRepository.save(user);

        // Get all booked seats for the flight and class
        List<String> bookedSeats = ticketRepository.findByFlightAndFlightClass(flight, flightClass)
                .stream()
                .map(Ticket::getSeatNumber)
                .collect(Collectors.toList());

        int nextAvailableSeat = 1;
        String seatNumber;

        // Generate tickets
        for (int i = 0; i < totalPassengers; i++) {
            do {
                seatNumber = seatPrefix + nextAvailableSeat;
                nextAvailableSeat++;
            } while (bookedSeats.contains(seatNumber));

            Ticket ticket = new Ticket();
            ticket.setTicketId(UUID.randomUUID().toString());
            ticket.setFlight(flight);
            ticket.setFlightClass(flightClass);
            ticket.setSeatFare(seatFare);
            ticket.setSeatNumber(seatNumber);
            ticket.setBookingTime(LocalDateTime.now());
            ticket.setStatus("Confirmed");
            ticket.setUser(user);

            ticketRepository.save(ticket);
        }

        // Return the last booked ticket as an example (assuming tickets are ordered by booking time)
        return ticketRepository.findFirstByUserOrderByBookingTimeDesc(user)
                .orElseThrow(() -> new RuntimeException("Error retrieving last booked ticket"));
    }

    @Override
    @Transactional
    public void cancelTicket(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Cancel ticket
        ticket.setStatus("Canceled");
        ticketRepository.save(ticket);

        // Adjust available seats in flight
        Flight flight = ticket.getFlight();
        switch (ticket.getFlightClass()) {
            case "Economy":
                flight.setAvailableSeatOfEconomyClass(flight.getAvailableSeatOfEconomyClass() + 1);
                break;
            case "Business":
                flight.setAvailableSeatOfBusinessClass(flight.getAvailableSeatOfBusinessClass() + 1);
                break;
            case "First Class":
                flight.setAvailableSeatOfFirstClass(flight.getAvailableSeatOfFirstClass() + 1);
                break;
            default:
                throw new RuntimeException("Invalid flight class");
        }

        // Update flight repository
        flightRepository.save(flight);

        // Adjust waiting list if available seats
        List<Ticket> waitingList = ticketRepository.findByFlightAndFlightClassAndStatusOrderByBookingTimeAsc(flight, ticket.getFlightClass(), "Waiting");
        for (Ticket waitingTicket : waitingList) {
            if (flight.getAvailableSeatsForClass(waitingTicket.getFlightClass()) > 0) {
                waitingTicket.setStatus("Confirmed");
                ticketRepository.save(waitingTicket);

                flight.reduceAvailableSeatForClass(waitingTicket.getFlightClass());
                flightRepository.save(flight);
            } else {
                break;
            }
        }
    }

    @Override
    public List<Ticket> getAllTicketsByflightNumber(String flightNumber) {
        return ticketRepository.findTicketsByFlightNumber(flightNumber);
    }

    @Override
    public List<Ticket> getAllConfirmTicketsByflightNumber(String flightNumber) {
        return ticketRepository.findAllConfirmTicketsByFlightNumber(flightNumber);
    }
}
