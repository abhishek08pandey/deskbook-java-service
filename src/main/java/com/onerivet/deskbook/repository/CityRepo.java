package com.onerivet.deskbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {

}
