package com.onerivet.deskbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.deskbook.models.entity.SeatNumber;

public interface SeatNumberRepo extends JpaRepository<SeatNumber, Integer> {

	@Query("SELECT s.id FROM SeatNumber s")
	public List<Integer> findAllId();
}
