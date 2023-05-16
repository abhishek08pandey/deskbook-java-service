package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.repository.ModeOfWorkRepo;
import com.onerivet.deskbook.services.ModeOfWorkService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ModeOfWorkServiceImpl implements ModeOfWorkService {
	
	
	@Autowired
	private ModeOfWorkRepo modeOfWorkRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ModeOfWorkDto> getAllModeOfWorks() {
		return this.modeOfWorkRepo.findAll().stream()
				.map((modeOfWork) -> this.modelMapper.map(modeOfWork, ModeOfWorkDto.class))
				.collect(Collectors.toList());
	}
}
