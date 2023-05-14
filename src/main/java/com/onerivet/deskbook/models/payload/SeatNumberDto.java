package com.onerivet.deskbook.models.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class SeatNumberDto {
	private int id;

	private String seatNumber;
	
	private ColumnDetailsDto columnDetails;
}
