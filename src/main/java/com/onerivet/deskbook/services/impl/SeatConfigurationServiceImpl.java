package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.repository.ColumnDetailsRepo;
import com.onerivet.deskbook.repository.FloorRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;
import com.onerivet.deskbook.services.SeatConfigurationService;

@Service
public class SeatConfigurationServiceImpl implements SeatConfigurationService {

	@Autowired
	private FloorRepo floorRepo;
	
	@Autowired
	private ColumnDetailsRepo columnDetailsRepo;
	
	@Autowired
	private SeatNumberRepo seatNumberRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<FloorDto> findAllFloor() {
		return floorRepo.findAll().stream().map((floor)->modelMapper.map(floor, FloorDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ColumnDetailsDto> findAllColumns() {
		return columnDetailsRepo.findAll().stream().map((columns)->modelMapper.map(columns, ColumnDetailsDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<SeatNumberDto> findAllSeatNumber() {
		return seatNumberRepo.findAll().stream().map((seatNumber)->modelMapper.map(seatNumber, SeatNumberDto.class)).collect(Collectors.toList());
	}
}
