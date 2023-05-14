package com.onerivet.deskbook.models.entity;


import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "[SeatConfiguration]", schema = "[dbo]")
public class SeatConfiguration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SeatConfigurationId")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "SeatId")
	private SeatNumber seatNumber;
	
	@OneToOne
	@JoinColumn(name = "CreatedBy")
	private Employee createdBy;
	
	@Column(name = "ModifiedDate")
	private LocalTime modifiedDate;
	
	@OneToOne
	@JoinColumn(name = "ModifiedBy")
	private Employee modifiedBy;
	
	@OneToOne
	@JoinColumn(name = "EmployeeId")
	private Employee employee;
	
}
