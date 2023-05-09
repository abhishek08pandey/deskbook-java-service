package com.onerivet.deskbook.models.payload;

import java.util.Set;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	private int id;

	private String userId;

	private String emailId;

	@NotEmpty(message = "Please enter your Name")
	@Size(min=2,message="First name must be of 3 characters or more")
	@Size(max=30,message="Your first name cannot be exceed 100 characters")
	@Pattern(regexp = "^[A-Z][a-z]+('[a-z]+)?$", message="First letter must be capital")
	@Pattern(regexp = "^[A-Z][a-z]+('[a-z]+)?$", message="Please enter valid first name")
	private String firstName;

	@NotEmpty(message = "Please enter your Name")
	@Size(min=2,message="First name must be of 3 characters or more")
	@Size(max=30,message="Your first name cannot be exceed 100 characters")
	@Pattern(regexp = "^[A-Z][a-z]+('[a-z]+)?$", message="First letter must be capital")
	@Pattern(regexp = "^[A-Z][a-z]+('[a-z]+)?$", message="Please enter valid first name")
	private String lastName;

	@Pattern(regexp = "^[0-9]{10}$",message="Please enter a numeric value only")
	@Size(min=10,max=10,message="10-digit number is required")
	@NotEmpty(message="Please enter phone no.")
	private String phoneNumber;

	@NotEmpty(message = "Please enter Project names")
	@Size(max = 200, message = "Project names cannot exceed 200 character")
	private String project;
	
	
	@NotNull(message = "Please select Mode of work")
	private ModeOfWorkDto modeOfWork;


	@NotNull(message = "Please select designation")
	private DesignationDto designation;


	@NotNull(message = "Please select seatConfiguration")
	private SeatConfigurationDto seatConfiguration;

	@NotEmpty(message = "Please select role")
	private Set<@Valid RoleDto> roles;

	private Set<@Valid WorkingDayDto> workingDays;

	private boolean active;
}
