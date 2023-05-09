package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.exception.ResorceNotFoundException;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.repository.EmployeeRepo;
import com.onerivet.deskbook.services.EmployeeService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

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
	public EmployeeDto update(EmployeeDto employeeDto) {

		EmployeeDto presentEmployee = getEmployeeById(employeeDto.getId());

		if (presentEmployee != null) {
			presentEmployee.setPhoneNumber(employeeDto.getPhoneNumber());
			presentEmployee.setDesignation(employeeDto.getDesignation());
			presentEmployee.setProject(employeeDto.getProject());
			presentEmployee.setModeOfWork(employeeDto.getModeOfWork());
			
			presentEmployee.setWorkingDays(employeeDto.getWorkingDays());
//			if (presentEmployee.getModeOfWork().equals(1)) { // Hybrid
//				
//				presentEmployee.setWorkingDays(employeeDto.getWorkingDays());
//				presentEmployee.setse
//			}
			
			Employee save = employeeRepo.save(modelMapper.map(presentEmployee, Employee.class));

			if(save!= null) {
				return modelMapper.map(save, EmployeeDto.class);
			}
		}

		return employeeDto;
	}

}
