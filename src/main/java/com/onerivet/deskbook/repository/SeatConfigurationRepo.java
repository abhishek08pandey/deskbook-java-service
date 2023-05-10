package com.onerivet.deskbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.SeatConfiguration;

public interface SeatConfigurationRepo extends JpaRepository<SeatConfiguration, Integer> {

}
