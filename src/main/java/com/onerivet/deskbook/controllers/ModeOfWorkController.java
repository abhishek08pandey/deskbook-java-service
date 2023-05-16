package com.onerivet.deskbook.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.ModeOfWorkService;

@RestController
@Validated
@RequestMapping("/api/deskbook")
public class ModeOfWorkController {
	
	@Autowired
	private ModeOfWorkService modeOfWorkService;
	
	/**
	 * @purpose: Get all mode of works 
	 * @return: List of modeOfWorkDto
	 */
	@GetMapping("/mode-of-works")
	public GenericResponse<List<ModeOfWorkDto>> getModeOfWorks() {
		GenericResponse<List<ModeOfWorkDto>> genericResponse = new GenericResponse<>(this.modeOfWorkService.getAllModeOfWorks(), null);
		return genericResponse;
	}
}
