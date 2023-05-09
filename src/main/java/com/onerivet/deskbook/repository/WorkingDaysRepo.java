package com.onerivet.deskbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.deskbook.models.entity.WorkingDay;

public interface WorkingDaysRepo extends JpaRepository<WorkingDay, Integer> {

	@Query("SELECT w.id FROM WorkingDay w")
	public List<Integer> findAllId();
}
