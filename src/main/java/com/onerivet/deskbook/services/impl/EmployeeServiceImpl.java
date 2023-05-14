package com.onerivet.deskbook.services.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.exception.InvalidInputException;
import com.onerivet.deskbook.exception.ResorceNotFoundException;
import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
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
import jakarta.xml.bind.DatatypeConverter;

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
	public EmployeeDto findByEmployeeId(String employeeId) throws ResorceNotFoundException {
		Employee employee = this.employeeRepo.findById(employeeId);
		return this.modelMapper.map(employee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto update(String employeeId, UpdateProfileDto updateProfileDto) throws IOException {

		Employee employee = employeeRepo.findById(employeeId);

		// String base64String = updateProfileDto.getProfilePictureFilePath();

		String fileExtension = base64ToFile(updateProfileDto.getProfilePictureFilePath(),
				updateProfileDto.getFirstName());

		System.out.println("4545: " + fileExtension);
		System.out.println("image store with this name: " + updateProfileDto.getFirstName() + fileExtension);

		employee.setProfilePictureFileName(updateProfileDto.getFirstName() + fileExtension);
		employee.setProfilePictureFilePath("D:\\deskbook\\" + updateProfileDto.getFirstName() + fileExtension);

		employee.setFirstName(updateProfileDto.getFirstName());
		employee.setLastName(updateProfileDto.getLastName());
		employee.setPhoneNumber(updateProfileDto.getPhoneNumber());
		employee.setDesignation(designationRepo.findById(updateProfileDto.getDesignation()).get());
		employee.setProject(updateProfileDto.getProject());
		employee.setModeOfWork(modeOfWorkRepo.findById(updateProfileDto.getModeOfWork()).get());
		employee.setModifiedDate(LocalDate.now());
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

//			seatConfiguration.setCity(city);
//			seatConfiguration.setFloor(floor);
//			seatConfiguration.setColumn(columnDetails);
//			seatConfiguration.setSeatNumber(seatNumber);
//			seatConfiguration.setEmployee(employee);
//			seatConfiguration.setCreatedBy(employee);
//			seatConfiguration.setModifiedBy(employee);
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
			// employeeDto.setSeatConfiguration(seatConfigurationDto);

			return employeeDto;
		}
	}

	private String base64ToFile(String base64String, String firstName) throws IOException {

		String base64 = validate(base64String);
		System.out.println(" validate: "+ base64);

		String fileExtension = base64.startsWith("/9j/") ? ".jpg" : ".jpeg"; // check for file extension

		byte[] decodedBytes = DatatypeConverter.parseBase64Binary(base64);
		// byte[] decodedBytes = Base64.getDecoder().decode(base64String);// Deprecated Error
		System.out.println(decodedBytes);
		System.out.println(decodedBytes[0]+"   and   "+decodedBytes[1]);
		
		FileOutputStream fos = new FileOutputStream("D:\\deskbook\\" + firstName + fileExtension);
		fos.write(decodedBytes);

		fos.close();
		System.out.println("fileExtension:  " + fileExtension);
		return fileExtension;
	}

	private String validate(String base64String) {

		// Decode the base64 string to binary data

		// Check magic number to ensure it's a valid JPEG file with Plain text-- just
		// the Base64 value

		if (base64String.startsWith("data:image/jpeg;base64,")){
			System.out.println("check jpeg ");

			return base64String.substring(23);
			
		} else if (base64String.startsWith("data:image/jpg;base64,")) {
			System.out.println("check  jpg ");

			return base64String.substring(22);
			
		} if (base64String.startsWith("/9j/")) {
			System.out.println("which is start with 9j:  " + base64String);
			return base64String;
		}
		System.out.println("not ");
		throw new InvalidInputException("Select .jpg or .jpeg file");
		
////		byte[] binaryData = DatatypeConverter.parseBase64Binary(base64String);
////		if (binaryData[0] != (byte) 0xFF || binaryData[1] != (byte) 0xD8) {
////			return false;
////		}
//
//		// Check the file extension to ensure it's .jpg or .jpeg with type Data
//		// URL--data:content/type;base64
//         if (!base64String.startsWith("data:image/jpeg;base64,") && !base64String.startsWith("data:image/jpg;base64,")) {
//            return false;
//        }
	}
}
