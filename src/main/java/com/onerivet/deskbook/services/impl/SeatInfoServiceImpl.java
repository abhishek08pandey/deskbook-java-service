package com.onerivet.deskbook.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.repository.CityRepo;
import com.onerivet.deskbook.repository.ColumnDetailsRepo;
import com.onerivet.deskbook.repository.FloorRepo;
import com.onerivet.deskbook.repository.ModeOfWorkRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;
import com.onerivet.deskbook.services.SeatInfoService;

@Service
public class SeatInfoServiceImpl implements SeatInfoService{

	@Autowired
	private ModeOfWorkRepo modeOfWorkRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private FloorRepo floorRepo;
	
	@Autowired
	private ColumnDetailsRepo columnDetailsRepo;
	
	@Autowired
	private SeatNumberRepo seatNumberRepo;
	
	
	@Override
	public List<Integer> findModeOfWorkAllId() {
		return modeOfWorkRepo.findAllId();
	}

	@Override
	public List<Integer> findCityAllId() {
		return cityRepo.findAllId();
	}

	@Override
	public List<Integer> findFloorAllId() {
		return floorRepo.findAllId();
	}

	@Override
	public List<Integer> findColumnAllId() {
		return columnDetailsRepo.findAllId();
	}

	@Override
	public List<Integer> findSeatNumberAllId() {
		return seatNumberRepo.findAllId();
	}

}
