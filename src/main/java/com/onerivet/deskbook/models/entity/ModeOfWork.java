package com.onerivet.deskbook.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "[ModeOfWork]", schema = "[Ref]")
public class ModeOfWork {
	@Id
	@Column(name = "ModeOfWorkId")
	private int id;
	
	@Column(name = "ModeOfWork")
	private String modeOfWorkName;
}
