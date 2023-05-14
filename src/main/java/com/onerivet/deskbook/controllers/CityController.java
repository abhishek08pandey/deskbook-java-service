package com.onerivet.deskbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.CityDto;
import com.onerivet.deskbook.services.CityService;


@RestController
@RequestMapping("/api/deskbook")
public class CityController {
	
	@Autowired
	private CityService cityService;
	 
	@GetMapping("/cities")
	public List<CityDto> findAllCity(){
		return cityService.findAllCity();
	}
}
