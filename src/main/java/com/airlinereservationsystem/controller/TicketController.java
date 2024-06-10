package com.airlinereservationsystem.controller;

import com.airlinereservationsystem.entity.Ticket;
import com.airlinereservationsystem.entity.TicketBookingRequest;
import com.airlinereservationsystem.repository.TicketRepository;
import com.airlinereservationsystem.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "https://airlineregervationsystem-frontend.onrender.com")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository repo;
    @PostMapping("/book/request")
    public Ticket bookTicketBySendingRequestParam(@RequestParam String email,
                             @RequestParam String flightNumber,
                             @RequestParam String flightClass,
                             @RequestParam int totalPassengers) {
        return ticketService.bookTicket(email, flightNumber, flightClass, totalPassengers);
    }
    
    @PostMapping("/book")
    public Ticket bookTicketBySendingRequestBody(@RequestBody TicketBookingRequest request) {
        return ticketService.bookTicket(
            request.getEmail(), 
            request.getFlightNumber(), 
            request.getFlightClass(), 
            request.getTotalPassengers()
        );
    }

    @PutMapping("/cancel/{ticketId}")
    public void cancelTicket(@PathVariable String ticketId) {
        ticketService.cancelTicket(ticketId);
    }
    
    @GetMapping("/get/{ticketId}")
    public Ticket getTicket(@PathVariable String ticketId) {
    	if(repo.findById(ticketId).isPresent())
    		return repo.findById(ticketId).get();
    	throw new RuntimeException("Ticket not found");
    }
    
    @GetMapping("/flight/{flightNumber}")
    public List<Ticket> getAllTicketByFlightNumber(@PathVariable String flightNumber){
		return ticketService.getAllTicketsByflightNumber(flightNumber);
    	
    }
    
    @GetMapping("/confirmed/flight/{flightNumber}")
    public List<Ticket> getAllConfirmTicketByFlightNumber(@PathVariable String flightNumber){
    	return ticketService.getAllConfirmTicketsByflightNumber(flightNumber);
    	
    }
}
