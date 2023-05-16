package com.hotel.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.bill.dto.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
