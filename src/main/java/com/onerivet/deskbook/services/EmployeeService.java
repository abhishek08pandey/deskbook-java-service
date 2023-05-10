package com.onerivet.deskbook.services;

import java.util.List;

import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;


public interface EmployeeService {

	public List<EmployeeDto> getAllEmployees();

	public EmployeeDto getEmployeeById(int id);
	
	public String update(int id,UpdateProfileDto employeeDto);
	
}
