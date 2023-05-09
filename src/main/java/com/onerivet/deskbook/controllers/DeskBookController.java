package com.onerivet.deskbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.EmployeeDto;
import com.onerivet.deskbook.services.EmployeeService;


@RestController
@RequestMapping("/api/deskbook")
public class DeskBookController {
	
	@GetMapping
	public Object test() {
		
		return """
				{
				  "message":"this a deskbook api"
				}
				""";
	}
	
	@Autowired		
	private EmployeeService employeeService;
	
	/**
	 * @purpose: Get all employees
	 * @return: List of employeeDto
	 */
	@GetMapping("/get-all-employees")
	public List<EmployeeDto> getAll() {
		return this.employeeService.getAllEmployees();
	}
	
	
	/**
	 * @purpose: Get employee by id
	 * @param: id
	 * @return: employeeDto
	 */
	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable("id") int id) {
		return this.employeeService.getEmployeeById(id);
	}
	
}
