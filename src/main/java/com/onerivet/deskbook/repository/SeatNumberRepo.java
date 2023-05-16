package com.onerivet.deskbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.SeatNumber;

public interface SeatNumberRepo extends JpaRepository<SeatNumber, Integer> {
	public List<SeatNumber> findByColumn(ColumnDetails column);
}
