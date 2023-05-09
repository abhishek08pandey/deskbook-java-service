
package com.onerivet.deskbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.Designation;

public interface DesignationRepo extends JpaRepository<Designation, Integer> {
	public Optional<Designation> findByDesignationName(String designationName);
}
