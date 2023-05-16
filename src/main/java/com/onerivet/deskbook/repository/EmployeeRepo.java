package com.onerivet.deskbook.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.deskbook.models.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String> {


}
