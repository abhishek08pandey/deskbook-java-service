package com.onerivet.deskbook.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.ModeOfWork;

public interface ModeOfWorkRepo extends JpaRepository<ModeOfWork, Integer> {
	
}
