package com.airlinereservationsystem.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Airplane {

	@Id
	private String registrationNumber;
	private String airplaneName;
	private String airplaneDescription;
	private int totalSeat;
	private int totalEconomyClassSeat;
	private int totalBusinessClassSeat;
	private int totalFirstClassSeat;
	
}
