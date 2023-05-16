package com.onerivet.deskbook.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.DesignationDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.DesignationService;

@RestController
@Validated
@RequestMapping("/api/deskbook")
public class DesignationController {
	
	@Autowired
	private DesignationService designationService;
	
	/**
	 * @purpose: Get all designations 
	 * @return: List of designationDto
	 */
	@GetMapping("/designations")
	public GenericResponse<List<DesignationDto>> getDesignations() {
		GenericResponse<List<DesignationDto>> genericResponse = new GenericResponse<>(this.designationService.getAllDesignations(), null);
		return genericResponse;
	}
}
