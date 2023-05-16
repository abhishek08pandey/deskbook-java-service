package com.onerivet.deskbook.models.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.WorkingDay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWorkingDaysDto {
	
	private int id;

	@JsonIgnore
	private Employee employee;

	private WorkingDay day;

	@JsonIgnore
	private Employee createdBy;
}
