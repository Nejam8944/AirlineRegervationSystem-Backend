package com.airlinereservationsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.airlinereservationsystem.entity.Ticket;
import com.airlinereservationsystem.entity.UserCredential;
import com.airlinereservationsystem.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "https://airlineregervationsystem-backend.onrender.com")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public void addCustomerToDb(@RequestBody UserCredential credential) {
        service.addCustomerToDb(credential);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> loginCustomer(@RequestBody UserCredential credential) {
        return service.loginCustomer(credential);
    }

    @GetMapping("/all")
    public List<UserCredential> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/wallet/balance/{email}")
    public double getWalletBalance(@PathVariable String email) {
        return service.getTotalBalance(email);
    }

    @PutMapping("/update/wallet/balance/{email}/{amount}")
    public void updateWalletBalance(@PathVariable String email, @PathVariable double amount) {
        service.updateTotalBalance(email, amount);
    }

    @PutMapping("/deduct/wallet/balance/{email}/{amount}")
    public void deductWalletBalance(@PathVariable String email, @PathVariable double amount) {
        service.deductTotalBalance(email, amount);
    }

    @GetMapping("/tickets/all")
    public List<Ticket> getAllTickets(@RequestParam String email) {
        return service.getAllTickets(email);
    }
    
    @GetMapping("/name/{email}")
    public String getNameOfUser(@PathVariable String email) {
    	return service.getNameOfUser(email);
    }
}
