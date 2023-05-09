package com.onerivet.deskbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.deskbook.models.entity.Floor;

public interface FloorRepo extends JpaRepository<Floor, Integer>{
	
	@Query("SELECT f.id FROM Floor f")
	public List<Integer> findAllId();
}
