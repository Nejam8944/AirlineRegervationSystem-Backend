package com.airlinereservationsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.airlinereservationsystem.entity.Ticket;
import com.airlinereservationsystem.entity.UserCredential;
import com.airlinereservationsystem.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	UserRepository repository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	AuthenticationManager authenticationManager; 

	

	@Override
	public void addCustomerToDb(@RequestBody UserCredential credential) {
		credential.setPassword(encoder.encode(credential.getPassword()));
		repository.save(credential);
	}
	
	
	@Override
	 public ResponseEntity<?> loginCustomer(@RequestBody UserCredential credential) {
		 try {
	            // Create authentication token
	            UsernamePasswordAuthenticationToken authenticationToken =
	                    new UsernamePasswordAuthenticationToken(credential.getEmail(), credential.getPassword());

	            // Authenticate the user
	            Authentication authentication = authenticationManager.authenticate(authenticationToken);
	            SecurityContextHolder.getContext().setAuthentication(authentication);

	            if(!credential.getUserRole().equals(repository.findUserRoleByEmail(credential.getEmail())))
	            	return ResponseEntity.ok("Invalid");
	            	
	            return ResponseEntity.ok("success");
	        } catch (AuthenticationException e) {
	            // If authentication fails
	            return ResponseEntity.status(401).body("Invalid email or password or role");
	        }
		 

	    }
	
	 @Override
	public List<UserCredential> getAllUsers() {
		return repository.findAll();
	}
	
	@Override
	public Double getTotalBalance(String email) {
		Optional<UserCredential> opt = repository.findById(email);
		if(opt.isEmpty())
			throw new RuntimeException("User not fund");
		return opt.get().getTotalBalance();
	}

	@Override
	public void updateTotalBalance(String email , double amount) {
		UserCredential user = repository.findById(email).get();
		user.setTotalBalance(amount);
		repository.save(user);
		
	}


	@Override
	public void deductTotalBalance(String email, double amount) {
		UserCredential user = repository.findById(email).get();
		double availableBalance = user.getTotalBalance()-amount;
		user.setTotalBalance(availableBalance);
		repository.save(user);
	}


	@Override
	public List<Ticket> getAllTickets(String email) {
		 List<Ticket> tickets = repository.findTicketsByUserEmail(email);
		 if(tickets==null || tickets.isEmpty())
			 throw new RuntimeException("No ticket Found for the user");
		return tickets;
	}


	@Override
	public String getNameOfUser(String email) {
		String firstName = repository.findNameByEmail(email).split("\\s+", 2)[0];
		return firstName;
	}

}
