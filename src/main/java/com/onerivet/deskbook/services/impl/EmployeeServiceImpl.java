package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.exception.ResorceNotFoundException;
import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;
import com.onerivet.deskbook.repository.CityRepo;
import com.onerivet.deskbook.repository.ColumnDetailsRepo;
import com.onerivet.deskbook.repository.DesignationRepo;
import com.onerivet.deskbook.repository.EmployeeRepo;
import com.onerivet.deskbook.repository.FloorRepo;
import com.onerivet.deskbook.repository.ModeOfWorkRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;
import com.onerivet.deskbook.services.EmployeeService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private DesignationRepo designationRepo;
	
	@Autowired private ModeOfWorkRepo modeOfWorkRepo;
	
	@Autowired private CityRepo cityRepo; 
	
	@Autowired private FloorRepo floorRepo;
	
	@Autowired private ColumnDetailsRepo columnDetailsRepo;
	
	@Autowired private SeatNumberRepo seatNumberRepo;
	
	@Autowired private SeatConfigurationRepo seatConfigurationRepo; 
	
	//@Autowired 	private SeatConfiguration seatConfiguration;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<EmployeeDto> getAllEmployees() {
		return this.employeeRepo.findAll().stream().map((employee) -> this.modelMapper.map(employee, EmployeeDto.class))
				.collect(Collectors.toList()); 
	}

	@Override
	public EmployeeDto getEmployeeById(int id) {
		Employee employee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResorceNotFoundException("Employee with id " + id + " not found."));
		return this.modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	public String update(int id,UpdateProfileDto employeeDto) {
		 
		Employee employee = employeeRepo.findById(id).get();
		
		employee.setFirstName(employeeDto.getFirstName()); 
		employee.setLastName(employeeDto.getLastName());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setDesignation(designationRepo.findById(employeeDto.getDesignation()).get());
		employee.setProject(employeeDto.getProject());
		employee.setModeOfWork(modeOfWorkRepo.findById(employeeDto.getModeOfWork()).get());
		
// SeatConfiguration add all sub entities
		
		City city = cityRepo.findById(employeeDto.getCity()).get();
		Floor floor = floorRepo.findById(employeeDto.getFloor()).get();
		ColumnDetails columnDetails = columnDetailsRepo.findById(employeeDto.getColumn()).get();
		SeatNumber seatNumber = seatNumberRepo.findById(employeeDto.getSeatNumber()).get();
		
		SeatConfiguration seatConfiguration = new SeatConfiguration();;
		
		seatConfiguration.setCity(city);
		seatConfiguration.setFloor(floor);
		seatConfiguration.setColumn(columnDetails);
		seatConfiguration.setSeatNumber(seatNumber);
		
		employeeRepo.save(employee);
		seatConfigurationRepo.save(seatConfiguration);
		
		return "ncbj";
	}
}
