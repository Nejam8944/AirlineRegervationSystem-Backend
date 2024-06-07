package com.airlinereservationsystem.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.airlinereservationsystem.entity.Ticket;
import com.airlinereservationsystem.entity.UserCredential;

public interface UserService {

	void addCustomerToDb(UserCredential credential);
	ResponseEntity<?> loginCustomer(UserCredential credential);
	Double getTotalBalance(String email);
	void updateTotalBalance(String email,double amount);
	void deductTotalBalance(String email,double amount);
	List<UserCredential> getAllUsers();
	List<Ticket> getAllTickets(String email);
	String getNameOfUser(String email);
}
