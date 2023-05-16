package com.onerivet.deskbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.CityDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.CityService;

@RestController
@Validated
@RequestMapping("/api/deskbook")
public class CityController {

	@Autowired
	private CityService cityService;

	/**
	 * @purpose: Get all cities
	 * @return: List of cityDto
	 */
	@GetMapping("/cities")
	public GenericResponse<List<CityDto>> getCities() {
		GenericResponse<List<CityDto>> genericResponse = new GenericResponse<>(this.cityService.getAllCities(), null);
		return genericResponse;
	}

}
