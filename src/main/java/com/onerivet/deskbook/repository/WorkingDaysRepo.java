package com.onerivet.deskbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.WorkingDay;

public interface WorkingDaysRepo extends JpaRepository<WorkingDay, Integer> {

}
