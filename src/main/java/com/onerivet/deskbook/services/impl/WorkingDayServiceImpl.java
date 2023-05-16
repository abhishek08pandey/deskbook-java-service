package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.models.payload.WorkingDayDto;
import com.onerivet.deskbook.repository.WorkingDaysRepo;
import com.onerivet.deskbook.services.WorkingDayService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class WorkingDayServiceImpl implements WorkingDayService {
	
	@Autowired
	private WorkingDaysRepo workingDaysRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<WorkingDayDto> getAllWorkingDays() {
		return this.workingDaysRepo.findAll().stream().map((day) -> this.modelMapper.map(day, WorkingDayDto.class))
				.collect(Collectors.toList());
	}

}
