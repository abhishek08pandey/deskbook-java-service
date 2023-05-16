package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.exception.ResourceNotFoundException;
import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.repository.ColumnDetailsRepo;
import com.onerivet.deskbook.repository.FloorRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;
import com.onerivet.deskbook.services.SeatConfigurationService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class SeatConfigurationServiceImpl implements SeatConfigurationService {
	
	@Autowired
	private FloorRepo floorRepo;
	
	@Autowired
	private ColumnDetailsRepo columnDetailsRepo;
	
	@Autowired
	private SeatNumberRepo seatNumberRepo;
	
	@Autowired
	private SeatConfigurationRepo seatConfigurationRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<FloorDto> getAllFloors(int cityId) {
		
		List<Floor> floors = this.floorRepo.findByCity(new City(cityId));
		if(floors.isEmpty()) {
			throw new ResourceNotFoundException("City does not exist!");
		}
		
		
		return floors.stream().map((floor) -> this.modelMapper.map(floor, FloorDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ColumnDetailsDto> getAllColumns(int floorId) {
		
		List<ColumnDetails> columns = this.columnDetailsRepo.findByFloor(new Floor(floorId));
		if(columns.isEmpty()) {
			throw new ResourceNotFoundException("Floor does not exist!");
		}
		
		return columns.stream()
				.map((column) -> this.modelMapper.map(column, ColumnDetailsDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<SeatNumberDto> getAllSeats(int columnId) {
		
		List<SeatNumber> seats = this.seatNumberRepo.findByColumn(new ColumnDetails(columnId));
		if(seats.isEmpty()) {
			throw new ResourceNotFoundException("Column does not exist!");
		}
		
		List<SeatNumber> bookedSeats = this.seatConfigurationRepo.findSeats(seats);

		for (int i = 0, j = 0; j < bookedSeats.size();) {
			if (seats.get(i).getId() == bookedSeats.get(j).getId()) {
				seats.get(i).setBooked(true);
				j++;
			}
			i++;
		}
		
		return seats.stream()
				.map((seatNumber) -> this.modelMapper.map(seatNumber, SeatNumberDto.class))
				.collect(Collectors.toList());
	}


}
