package com.onerivet.deskbook.services;

import java.util.List;

import com.onerivet.deskbook.models.payload.EmployeeDto;


public interface EmployeeService {

	public List<EmployeeDto> getAllEmployees();

	public EmployeeDto getEmployeeById(int id);
	
	public EmployeeDto update(EmployeeDto employeeDto);
	
}
