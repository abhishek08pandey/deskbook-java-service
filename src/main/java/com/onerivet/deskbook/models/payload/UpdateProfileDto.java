package com.onerivet.deskbook.models.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor 
public class UpdateProfileDto {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private int designation;	
	private String project;
	private int modeOfWork;

	private int city;

	private int floor;

	private int column;

	private int seatNumber;
}
