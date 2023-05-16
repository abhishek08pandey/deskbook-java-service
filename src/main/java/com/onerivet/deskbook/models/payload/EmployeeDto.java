package com.onerivet.deskbook.models.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	private String id;

	private String emailId;

	private String firstName;

	private String lastName;

	private String phoneNumber;

	private String project;
	
	private String profilePictureFileName;
	
	private String profilePictureFilePath;
	
	private ModeOfWorkDto modeOfWork;

	private DesignationDto designation;

	private boolean active;
}
