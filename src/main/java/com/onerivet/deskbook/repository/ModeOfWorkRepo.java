package com.onerivet.deskbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.ModeOfWork;

public interface ModeOfWorkRepo extends JpaRepository<ModeOfWork, Integer> {
	public Optional<ModeOfWork> findByModeOfWorkName(String modeOfWorkName);
}
