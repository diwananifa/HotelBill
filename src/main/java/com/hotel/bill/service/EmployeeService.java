package com.hotel.bill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.bill.dao.EmployeeDao;
import com.hotel.bill.dto.Employee;
import com.hotel.bill.exception.DuplicateEmailFoundException;
import com.hotel.bill.exception.NoDataFoundException;
import com.hotel.bill.exception.NoSuchEmailFoundException;
import com.hotel.bill.exception.NoSuchIdFoundException;
import com.hotel.bill.util.ResponseStructure;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee e) {

		Employee employee = employeeDao.saveEmployee(e);
		if (employee != null) {
			ResponseStructure<Employee> responseStructure = new ResponseStructure<>();
			ResponseEntity<ResponseStructure<Employee>> entity = new ResponseEntity<>(responseStructure,
					HttpStatus.CREATED);

			responseStructure.setStatuscode(HttpStatus.CREATED.value());
			responseStructure.setMessage("employee saved successfully");
			responseStructure.setData(employee);
			return entity;

		} else {
			throw new DuplicateEmailFoundException();
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> findEmpolyeeById(int id) {

		Employee employee = employeeDao.findEmployeeById(id);
		if (employee != null) {
			ResponseStructure<Employee> responseStructure = new ResponseStructure<>();
			ResponseEntity<ResponseStructure<Employee>> entity = new ResponseEntity<>(responseStructure, HttpStatus.OK);

			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Employee details");
			responseStructure.setData(employee);

			return entity;
		} else {
			throw new NoSuchIdFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findAll() {
		List<Employee> list = employeeDao.getAllEmployee();

		if (list.size() > 0) {
			ResponseStructure<List<Employee>> responseStructure = new ResponseStructure<>();
			ResponseEntity<ResponseStructure<List<Employee>>> entity = new ResponseEntity<>(responseStructure,
					HttpStatus.OK);

			responseStructure.setMessage("details of employees");
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setData(employeeDao.getAllEmployee());

			return entity;
		} else {
			throw new NoDataFoundException();
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id, Employee e) {

		Employee employee = employeeDao.findEmployeeById(id);

		if (employee != null) {
			employee.setName(e.getName());
			employee.setEmail(e.getEmail());
			employee.setPassword(e.getPassword());
			employee.setRole(e.getRole());

			ResponseStructure<Employee> responseStructure = new ResponseStructure<>();
			ResponseEntity<ResponseStructure<Employee>> entity = new ResponseEntity<>(responseStructure,
					HttpStatus.CREATED);

			responseStructure.setStatuscode(HttpStatus.CREATED.value());
			responseStructure.setMessage("updated successfully");
			responseStructure.setData(employeeDao.updateEmployee(employee));

			return entity;
		} else {
			throw new NoSuchIdFoundException();
		}

	}

	public ResponseEntity<ResponseStructure<String>> deleteEmployee(int id) {

		Employee employee = employeeDao.findEmployeeById(id);

		if (employee != null) {

			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			ResponseEntity<ResponseStructure<String>> entity = new ResponseEntity<>(responseStructure,
					HttpStatus.NOT_FOUND);

			responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("deleted successfully");
			responseStructure.setData(employeeDao.deleteEmployee(id));

			return entity;
		} else {
			throw new NoSuchIdFoundException();

		}

	}

	public ResponseEntity<ResponseStructure<Employee>> findEmpolyeeByEmail(String mail) {

		Employee employee = employeeDao.findEmployeeByMail(mail);
		if (employee != null) {
			ResponseStructure<Employee> responseStructure = new ResponseStructure<>();
			ResponseEntity<ResponseStructure<Employee>> entity = new ResponseEntity<>(responseStructure, HttpStatus.OK);

			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Employee details");
			responseStructure.setData(employee);

			return entity;
		} else {
			throw new NoSuchEmailFoundException();
		}
	}

}
