package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.Set;
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
import com.onerivet.deskbook.models.entity.WorkingDay;
import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.models.payload.SeatConfigurationDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;
import com.onerivet.deskbook.repository.CityRepo;
import com.onerivet.deskbook.repository.ColumnDetailsRepo;
import com.onerivet.deskbook.repository.DesignationRepo;
import com.onerivet.deskbook.repository.EmployeeRepo;
import com.onerivet.deskbook.repository.FloorRepo;
import com.onerivet.deskbook.repository.ModeOfWorkRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;
import com.onerivet.deskbook.repository.WorkingDaysRepo;
import com.onerivet.deskbook.services.EmployeeService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private DesignationRepo designationRepo;

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

	@Autowired
	private SeatConfigurationRepo seatConfigurationRepo;
	
	@Autowired
	private WorkingDaysRepo workingDaysRepo;

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
	public EmployeeDto update(int id, UpdateProfileDto updateProfileDto) {

		Employee employee = employeeRepo.findById(id).get();

		employee.setProfilePictureFileName(updateProfileDto.getProfilePictureFileName());
		employee.setProfilePictureFilePath(updateProfileDto.getProfilePictureFilePath());
		employee.setFirstName(updateProfileDto.getFirstName());
		employee.setLastName(updateProfileDto.getLastName());
		employee.setPhoneNumber(updateProfileDto.getPhoneNumber());
		employee.setDesignation(designationRepo.findById(updateProfileDto.getDesignation()).get());
		employee.setProject(updateProfileDto.getProject());
		employee.setModeOfWork(modeOfWorkRepo.findById(updateProfileDto.getModeOfWork()).get());
		employee.setModifiedBy(employee);
		
// Check ModeOfWork	
		int modeOfWork = updateProfileDto.getModeOfWork();
// If It is Work From home
		if (modeOfWork == 2) {

			employeeRepo.save(employee);
			EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

			return employeeDto;
		} else {

// SeatConfiguration add all sub entities drop down values 

			City city = cityRepo.findById(updateProfileDto.getCity()).get();
			Floor floor = floorRepo.findById(updateProfileDto.getFloor()).get();
			ColumnDetails columnDetails = columnDetailsRepo.findById(updateProfileDto.getColumn()).get();
			SeatNumber seatNumber = seatNumberRepo.findById(updateProfileDto.getSeatNumber()).get();

			SeatConfiguration seatConfiguration = new SeatConfiguration();

			seatConfiguration.setCity(city);
			seatConfiguration.setFloor(floor);
			seatConfiguration.setColumn(columnDetails);
			seatConfiguration.setSeatNumber(seatNumber);
			seatConfiguration.setEmployee(employee);
			seatConfiguration.setCreatedBy(employee);
			seatConfiguration.setModifiedBy(employee);
			// seatConfiguration.setAvailable(employeeDto.isAvailable());
			
// For Regular Employee
			
// For Hybrid work
//			List<WorkingDay> findAllById = workingDaysRepo.findAllById(updateProfileDto.getDay());
//			
//			employee.getWorkingDays().addAll(findAllById);
			
			employeeRepo.save(employee);
			seatConfigurationRepo.save(seatConfiguration);

			EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
			SeatConfigurationDto seatConfigurationDto = modelMapper.map(seatConfiguration, SeatConfigurationDto.class);
			employeeDto.setSeatConfiguration(seatConfigurationDto);

			return employeeDto;
		}
	}
}
