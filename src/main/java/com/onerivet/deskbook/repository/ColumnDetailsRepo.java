package com.onerivet.deskbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Floor;

public interface ColumnDetailsRepo extends JpaRepository<ColumnDetails, Integer> {
	public List<ColumnDetails> findByFloor(Floor floor);
}
