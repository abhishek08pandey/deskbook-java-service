package com.onerivet.deskbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.deskbook.models.entity.ModeOfWork;

public interface ModeOfWorkRepo extends JpaRepository<ModeOfWork, Integer> {
	
	public Optional<ModeOfWork> findByModeOfWorkName(String modeOfWorkName);
	
	@Query("SELECT m.id FROM ModeOfWork m")
	public List<Integer> findAllId();
}
