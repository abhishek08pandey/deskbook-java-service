package com.onerivet.deskbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.deskbook.models.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {

	@Query("SELECT c.id FROM City c")
	public List<Integer> findAllId();
}
