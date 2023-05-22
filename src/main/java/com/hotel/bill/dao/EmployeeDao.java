package com.hotel.bill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.bill.dto.Employee;
import com.hotel.bill.repository.EmployeeRepo;

@Repository
public class EmployeeDao {

	@Autowired
	EmployeeRepo repo;
	
	public Employee findEmployeeById(int id) {
		Optional<Employee> optional= repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	public Employee saveEmployee(Employee e) {
		return repo.save(e);
	}
	
	public List<Employee> getAllEmployee(){
		return repo.findAll();
	}
	
	public Employee updateEmployee(Employee e) {
		return repo.save(e);
	}
	
	public String deleteEmployee(int id) {
		repo.deleteById(id);
		return "deleted";
	}
	
	public Employee findEmployeeByMail(String email) {
		return	 repo.findByEmail(email);
	}
}
