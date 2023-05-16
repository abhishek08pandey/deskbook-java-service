package com.onerivet.deskbook.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.models.payload.CityDto;
import com.onerivet.deskbook.repository.CityRepo;
import com.onerivet.deskbook.services.CityService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CityDto> getAllCities() {
		return this.cityRepo.findAll().stream().map((city) -> this.modelMapper.map(city, CityDto.class))
				.collect(Collectors.toList());
	}
}
