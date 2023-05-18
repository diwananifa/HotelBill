package com.hotel.bill.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.bill.dto.Employee;
import com.hotel.bill.service.EmployeeService;
import com.hotel.bill.util.ResponseStructure;

@RestController
public class EmployeeContoller {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("saveemployee")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee e){
		return employeeService.saveEmployee(e);
	}
	
	@GetMapping("getemployeebyid")
	public ResponseEntity<ResponseStructure<Employee>> findEmpolyeeById(@RequestParam int id){
		return employeeService.findEmpolyeeById(id);
	}
	
	@GetMapping("getallemployee")
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployee(){
		return employeeService.findAll();
	}
	
	@PutMapping("updateemployee")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestParam int id,@RequestBody Employee e){
		return employeeService.updateEmployee(id, e);
	}
	
	@DeleteMapping("deleteemployee")
	public ResponseEntity<ResponseStructure<String>> deleteEmployee(@RequestParam int id){
		return employeeService.deleteEmployee(id);
	}
	
	@GetMapping("getemployeebyemail")
	public ResponseEntity<ResponseStructure<Employee>> findEmpolyeeByEmail(@RequestParam String mail){
		return employeeService.findEmpolyeeByEmail(mail);
	}
	
}
