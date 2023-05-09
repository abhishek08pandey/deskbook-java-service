package com.onerivet.deskbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.deskbook.models.entity.ColumnDetails;

public interface ColumnDetailsRepo extends JpaRepository<ColumnDetails, Integer> {

	@Query("SELECT c.id FROM ColumnDetails c")
	public List<Integer> findAllId();
}
