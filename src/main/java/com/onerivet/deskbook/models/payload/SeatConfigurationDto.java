package com.onerivet.deskbook.models.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatConfigurationDto {
	private int id;

	private CityDto city;

	private FloorDto floor;

	private ColumnDetailsDto column;

	private SeatNumberDto seatNumber;
}
