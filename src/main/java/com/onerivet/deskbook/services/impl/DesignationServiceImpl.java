package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.models.payload.DesignationDto;
import com.onerivet.deskbook.repository.DesignationRepo;
import com.onerivet.deskbook.services.DesignationService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class DesignationServiceImpl implements DesignationService {
	
	@Autowired
	private DesignationRepo designationRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<DesignationDto> getAllDesignations() {
		return this.designationRepo.findAll().stream()
				.map((designation) -> this.modelMapper.map(designation, DesignationDto.class))
				.collect(Collectors.toList());
	}

}
