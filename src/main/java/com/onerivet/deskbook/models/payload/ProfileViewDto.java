package com.onerivet.deskbook.models.payload;

import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileViewDto {
	

	private String profilePictureFilePath;

	private String emailId;

	private String firstName;

	private String lastName;

	private String phoneNumber;

	private DesignationDto designation;
	
	private ModeOfWorkDto modeOfWork;

	private String project;

	private CityDto city;

	private FloorDto floor;

	private ColumnDetailsDto column;

	private SeatNumberDto seat;
	
	private Set<WorkingDayDto> days;

}
