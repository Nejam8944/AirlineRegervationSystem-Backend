package com.airlinereservationsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserCredential {

    @Id
    private String email;
    private String name;
    private String password;
    private String userRole;
    private String contactNumber;
    @PositiveOrZero
    private double totalBalance;
}
