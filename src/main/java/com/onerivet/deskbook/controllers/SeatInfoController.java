package com.onerivet.deskbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.services.EmployeeService;
import com.onerivet.deskbook.services.SeatInfoService;

@RestController
@RequestMapping("/api/seat-info")
public class SeatInfoController {

	@Autowired		
	private EmployeeService employeeService;
	
	@PutMapping("/update")
	public EmployeeDto update(@RequestBody EmployeeDto employeeDto) {
		return employeeService.update(employeeDto);
	}
	
	@Autowired
	private SeatInfoService seatInfoService;

	@GetMapping("/mode-of-work-id")
	public List<Integer> findModeOfWorkAllId(){
		return seatInfoService.findModeOfWorkAllId();
	}

	@GetMapping("/city-id")
	public List<Integer> findCityAllId(){
		return seatInfoService.findCityAllId();
	}

	@GetMapping("/floor-id")
	public List<Integer> findFloorAllId(){
		return seatInfoService.findFloorAllId();
	}

	@GetMapping("/column-id")
	public List<Integer> findColumnAllId(){
		return seatInfoService.findColumnAllId();
	}

	@GetMapping("/seat-id")
	public List<Integer> findSeatNumberAllId(){
		return seatInfoService.findSeatNumberAllId();
	}

}
