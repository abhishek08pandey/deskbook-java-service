package com.onerivet.deskbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.services.SeatConfigurationService;

@RestController
@RequestMapping("/api/deskbook")
public class SeatConfigurationController {

	@Autowired
	private SeatConfigurationService buildingService;
	
	@GetMapping("/get-all-Floors")
	public List<FloorDto> findAllFloor(){
		return buildingService.findAllFloor();
	}
	
	@GetMapping("/get-all-columns")
	public List<ColumnDetailsDto> findAllColumns(){
		return buildingService.findAllColumns();
	}
	
	@GetMapping("/get-all-seats")
	public List<SeatNumberDto> findAllSeatNumber(){
		return buildingService.findAllSeatNumber();
	}
}
