package com.onerivet.deskbook.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.WorkingDayDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.WorkingDayService;

@RestController
@Validated
@RequestMapping("/api/deskbook")
public class WorkingDayController {
	
	@Autowired
	private WorkingDayService workingDayService;
	
	/**
	 * @purpose: Get all working days 
	 * @return: List of workingDayDto
	 */
	@GetMapping("/working-days")
	public GenericResponse<List<WorkingDayDto>> getWorkingDays() {
		GenericResponse<List<WorkingDayDto>> genericResponse = new GenericResponse<>(this.workingDayService.getAllWorkingDays(), null);
		return genericResponse;
	}
}
