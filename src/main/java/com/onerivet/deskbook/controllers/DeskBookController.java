package com.onerivet.deskbook.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.models.payload.UpdateProfileDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.EmployeeService;
import com.onerivet.deskbook.services.ModeOfWorkService;

import jakarta.validation.Valid;

 
@RestController
@RequestMapping("/api/deskbook")
public class DeskBookController {
	
	@Autowired		
	private EmployeeService employeeService;
	
	@GetMapping
	public Object test() {
		
		return """ 
				{
				  "message":"this a deskbook api"
				}
				""";
	}
	
	/**
	 * @purpose: Get all employees
	 * @return: List of employeeDto
	 */
	@GetMapping("/employees")
	public GenericResponse<List<EmployeeDto>> getAll() {
		
		List<EmployeeDto> employeeDto = employeeService.getAllEmployees();
		 return new GenericResponse<>(employeeDto, null);
	}
	
	/**
	 * @purpose: Get employee by id 
	 * @param: id
	 * @return: employeeDto
	 */
	@GetMapping("/{employeeId}")
	public GenericResponse<EmployeeDto> getById(@PathVariable("employeeId") String employeeId) {
		return new GenericResponse<EmployeeDto>(employeeService.findByEmployeeId(employeeId), null);
	}
	
	@PutMapping("/update/{employeeId}")
	public EmployeeDto update(@PathVariable("employeeId") String employeeId, @Valid @RequestBody UpdateProfileDto employeeDto) throws IOException {
		
		return employeeService.update(employeeId, employeeDto);
	}
}
