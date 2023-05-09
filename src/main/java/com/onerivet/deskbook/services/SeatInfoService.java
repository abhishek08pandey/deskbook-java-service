package com.onerivet.deskbook.services;

import java.util.List;

public interface SeatInfoService {

	public List<Integer> findModeOfWorkAllId();
	
	public List<Integer> findCityAllId();
	
	public List<Integer> findFloorAllId();
	
	public List<Integer> findColumnAllId();
	
	public List<Integer> findSeatNumberAllId();
}
