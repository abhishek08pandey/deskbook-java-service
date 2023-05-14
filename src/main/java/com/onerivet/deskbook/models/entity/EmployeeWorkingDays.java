package com.onerivet.deskbook.models.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "[EmployeeWorkingDays]" , schema = "dbo")
public class EmployeeWorkingDays {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EmployeeWorkingDayId")
	private int id;
	
	@OneToOne//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "EmployeeId")
	private Employee employeeId;
	
	@ManyToMany
	@JoinColumn(name = "WorkingDayId")
	private List<WorkingDay> workingDayId;
	
	@OneToOne//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CreatedBy")
	private Employee createdBy;
		
	@Column(name = "ModifiedDate")
	private LocalDate modifiedDate;
	
	@OneToOne
	@JoinColumn(name = "ModifiedBy")
	private Employee modifiedBy;
	
	@Column(name = "DeletedDate")
	private LocalDate deletedDate;
	
	@OneToOne
	@JoinColumn(name = "DeletedBy")
	private Employee deletedBy;
}
