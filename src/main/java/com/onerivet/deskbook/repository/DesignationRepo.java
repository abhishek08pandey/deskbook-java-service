
package com.onerivet.deskbook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.deskbook.models.entity.Designation;

public interface DesignationRepo extends JpaRepository<Designation, Integer> {
	public Optional<Designation> findByDesignationName(String designationName);
	
	@Query("SELECT d.id FROM Designation d")
	public List<Integer> findAllId();
}
