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
@Table(name = "[Column]", schema = "[Ref]")
public class ColumnDetails {
	@Id
	@Column(name = "ColumnId")
	private int id;
	
	@Column(name = "ColumnName")
	private char columnName;
}
