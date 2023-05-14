package com.onerivet.deskbook.services;

import java.io.IOException;
import java.util.List;

import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;


public interface EmployeeService {

	public List<EmployeeDto> getAllEmployees();

	public EmployeeDto findByEmployeeId(String employeeId);
	
	public EmployeeDto update(String employeeId, UpdateProfileDto employeeDto) throws IOException;
	
}
