package com.onerivet.deskbook.services;

import java.util.List;

import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;

public interface SeatConfigurationService {

	public List<FloorDto> findAllFloor();

	public List<ColumnDetailsDto> findAllColumns();
	
	public List<SeatNumberDto> findAllSeatNumber();


}
