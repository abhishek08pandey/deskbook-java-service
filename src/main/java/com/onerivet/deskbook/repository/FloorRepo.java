package com.onerivet.deskbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.Floor;

public interface FloorRepo extends JpaRepository<Floor, Integer>{
	public List<Floor> findByCity(City city);
}
