package com.airlinereservationsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Airport {

	@Id
	private String AirportCode;
	private String AirportName;
	private String AirportLocation;
	private String AirportAddress;
}
