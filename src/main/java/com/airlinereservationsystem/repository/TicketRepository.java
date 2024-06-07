package com.airlinereservationsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airlinereservationsystem.entity.Flight;
import com.airlinereservationsystem.entity.Ticket;
import com.airlinereservationsystem.entity.UserCredential;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

	Optional<Ticket> findFirstByUserOrderByBookingTimeDesc(UserCredential user);
	
	@Query("SELECT t FROM Ticket t WHERE t.flight.flightNumber = :flightNumber")
	List<Ticket> findTicketsByFlightNumber(@Param("flightNumber") String flightNumber);
	
	@Query("SELECT t FROM Ticket t WHERE t.flight.flightNumber = :flightNumber AND t.status = 'Confirmed'")
	List<Ticket> findAllConfirmTicketsByFlightNumber(@Param("flightNumber") String flightNumber);

//	Optional<Flight> findByFlightAndFlightClass(Flight flight, String flightClass);
	
	List<Ticket> findByFlightAndFlightClass(Flight flight, String flightClass);

	@Query("SELECT COUNT(t) FROM Ticket t WHERE t.flight = :flight AND t.flightClass = :flightClass AND t.status = 'Waiting'")
    int countWaitingListTickets(@Param("flight") Flight flight, @Param("flightClass") String flightClass);


	List<Ticket> findByFlightAndFlightClassAndStatusOrderByBookingTimeAsc(Flight flight, String flightClass,
			String string);

}
