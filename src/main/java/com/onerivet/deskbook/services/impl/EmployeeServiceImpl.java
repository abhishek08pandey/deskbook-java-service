package com.onerivet.deskbook.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.exception.ResourceNotFoundException;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.EmployeeWorkingDays;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.models.payload.ProfileViewDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;
import com.onerivet.deskbook.repository.DesignationRepo;
import com.onerivet.deskbook.repository.EmployeeRepo;
import com.onerivet.deskbook.repository.EmployeeWorkingDaysRepo;
import com.onerivet.deskbook.repository.ModeOfWorkRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;
import com.onerivet.deskbook.repository.WorkingDaysRepo;
import com.onerivet.deskbook.services.EmployeeService;
import com.onerivet.deskbook.util.ImageUtils;
import com.onerivet.deskbook.util.ProfileMapper;

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
	private SeatNumberRepo seatNumberRepo;

	@Autowired
	private WorkingDaysRepo workingDaysRepo;

	@Autowired
	private SeatConfigurationRepo seatConfigurationRepo;

	@Autowired
	private EmployeeWorkingDaysRepo employeeWorkingDaysRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProfileMapper profileMapper;
	
	@Autowired
	private ImageUtils imageUtils;
	
	@Value("${image.upload.path}")
	String path;
	

	@Override
	public List<EmployeeDto> getAllEmployees() {
		return this.employeeRepo.findAll().stream().map((employee) -> this.modelMapper.map(employee, EmployeeDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProfileViewDto getEmployeeById(String id) throws Exception {
		Employee employee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + " not found."));

		SeatConfiguration seatConfiguration = seatConfigurationRepo.findByEmployee(employee);

		return profileMapper.getProfile(employee, seatConfiguration);
	}

	@Override
	public ProfileViewDto updateEmpById(String id, UpdateProfileDto newEmployeeDto) throws Exception {

		Employee employee = this.employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee With id " + id + " not found."));
		SeatConfiguration savedSeatConfiguration = null;
		
		

		if ((newEmployeeDto.getModeOfWork() == 3 || newEmployeeDto.getModeOfWork() == 2)  && newEmployeeDto.getWorkingDays() != null
				&& newEmployeeDto.getWorkingDays().length != 0) {
			throw new IllegalArgumentException("You cannot select working day");
		}
			

		if(newEmployeeDto.getModeOfWork() == 1 && newEmployeeDto.getWorkingDays().length >= 5)
			throw new IllegalArgumentException("You can select Maximum 4 days");
		
		if(newEmployeeDto.getModeOfWork() == 1 && newEmployeeDto.getWorkingDays() == null) {
			throw new IllegalArgumentException("Please select Days");
		}

		
		if(newEmployeeDto.getProfilePictureFilePath() != null) {
			String fileExtension = imageUtils.base64ToFile(newEmployeeDto.getProfilePictureFilePath(),
					newEmployeeDto.getFirstName(), employee.getId());
			employee.setProfilePictureFileName(newEmployeeDto.getFirstName() + fileExtension);
			employee.setProfilePictureFilePath(path + newEmployeeDto.getFirstName()+ "_" + employee.getId() + fileExtension);
		
		}
			
	
		employee.setFirstName(newEmployeeDto.getFirstName());
		employee.setLastName(newEmployeeDto.getLastName());
		employee.setPhoneNumber(newEmployeeDto.getPhoneNumber());
		employee.setProject(newEmployeeDto.getProject());

		employee.setDesignation(this.designationRepo.findById(newEmployeeDto.getDesignation())
				.orElseThrow(() -> new ResourceNotFoundException("Designation With id " + id + " not found.")));
		employee.setModeOfWork(this.modeOfWorkRepo.findById(newEmployeeDto.getModeOfWork())
				.orElseThrow(() -> new ResourceNotFoundException("Mode of work With id " + id + " not found.")));

		employee.setModifiedBy(employee);
		employee.setModifiedDate(LocalDateTime.now());

		if (employee.getModeOfWork().getId() != 2) {

			SeatNumber seatNumber = this.seatNumberRepo.findById(newEmployeeDto.getSeat())
					.orElseThrow(() -> new ResourceNotFoundException("Seat With id " + id + " not found."));

			seatNumber.setBooked(true);
			
			Map<String, ColumnDetails> map = seatConfigurationRepo.findColumnFloorCityBySeat(seatNumber);

			ColumnDetails columnDetails = map.get("Column");
			if (columnDetails != null) {
				if (columnDetails.getFloor().getCity().getId() != newEmployeeDto.getCity())
					throw new ResourceNotFoundException("Enter valid city");

				if (columnDetails.getFloor().getId() != newEmployeeDto.getFloor())
					throw new ResourceNotFoundException("Enter valid floor");

				if (columnDetails.getId() != newEmployeeDto.getColumn())
					throw new ResourceNotFoundException("Enter Valid column");
			}

			SeatConfiguration employeeSeat = this.seatConfigurationRepo.findByEmployee(employee);

			SeatConfiguration bookedSeat = this.seatConfigurationRepo.findBySeatNumber(seatNumber);

			if (employeeSeat == null) {
				employeeSeat = new SeatConfiguration();
			}

			if (bookedSeat != null && bookedSeat.getEmployee().getId() != employee.getId())
				throw new IllegalArgumentException("Already Booked");
			
			employeeSeat.setCreatedBy(employee);
			employeeSeat.setEmployee(employee);
			employeeSeat.setModifiedBy(employee);
			employeeSeat.setModifiedDate(LocalDateTime.now());
			employeeSeat.setSeatNumber(seatNumber);

			List<EmployeeWorkingDays> employeeWorkingDays = employeeWorkingDaysRepo.findByEmployee(employee);

			for (EmployeeWorkingDays day : employeeWorkingDays) {
				day.setDeletedBy(employee);
				day.setDeletedDate(LocalDateTime.now());
				employeeWorkingDaysRepo.save(day);
			}

			if (employee.getModeOfWork().getModeOfWorkName().equalsIgnoreCase("Hybrid")) {

				for (int i : newEmployeeDto.getWorkingDays()) {
					employeeWorkingDaysRepo.save(new EmployeeWorkingDays(employee,
							this.workingDaysRepo.findById(i).get(), employee, employee, LocalDateTime.now()));
				}
			}

			savedSeatConfiguration = seatConfigurationRepo.save(employeeSeat);
		}  

		Employee savedEmployee = this.employeeRepo.save(employee);

		return profileMapper.getProfile(savedEmployee, savedSeatConfiguration);

	}
}
