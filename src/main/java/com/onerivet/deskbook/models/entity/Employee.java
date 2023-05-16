package com.onerivet.deskbook.models.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "[Employee]", schema = "[dbo]")
public class Employee {
	@Id
	@Column(name = "EmployeeId")
	private String id;

	@Column(name = "EmailId")
	private String emailId;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "ProjectName")
	private String project;
	
	@Column(name = "ProfilePictureFileName")
	private String profilePictureFileName;
	
	@Column(name = "ProfilePictureFilePath")
	private String profilePictureFilePath;

	@OneToOne
	@JoinColumn(name = "ModeOfWorkId")
	private ModeOfWork modeOfWork;

	@OneToOne
	@JoinColumn(name = "DesignationId")
	private Designation designation;
	
	@Column(name = "ModifiedDate")
	private LocalDateTime modifiedDate;
	
	@OneToOne
	@JoinColumn(name = "ModifiedBy")
	private Employee modifiedBy;
	
	@Column(name = "IsActive")
	private boolean active;
	

}
