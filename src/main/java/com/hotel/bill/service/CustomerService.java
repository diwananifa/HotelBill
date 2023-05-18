package com.hotel.bill.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.hotel.bill.dao.CustomerDao;
import com.hotel.bill.dto.Customer;
import com.hotel.bill.exception.NoSuchIdFoundException;
import com.hotel.bill.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;

	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
		ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Customer>> entity = new ResponseEntity<>(responseStructure,
				HttpStatus.CREATED);
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("customer details saved successfully");
		responseStructure.setData(customerDao.saveCustomer(customer));
		return entity;
	}

	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(int id) {
		Customer customer = customerDao.getCustomerById(id);
		if (customer != null) {
			ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
			ResponseEntity<ResponseStructure<Customer>> entity = new ResponseEntity<>(responseStructure, HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("customer details fetched successfully based on id");
			responseStructure.setData(customerDao.getCustomerById(id));
			return entity;
		} else {
			throw new NoSuchIdFoundException("customer id is not found");
		}
	}
	
//	public ResponseEntity<ResponseStructure<Customer>> getCustomerByName(String name) {
//		Customer customer=customerDao.getCustomerByName(name);
//		if (customer != null) {
//			ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
//			ResponseEntity<ResponseStructure<Customer>> entity = new ResponseEntity<>(responseStructure, HttpStatus.OK);
//			responseStructure.setStatuscode(HttpStatus.OK.value());
//			responseStructure.setMessage("customer details fetched successfully based on name");
//			responseStructure.setData(customer);
//			return entity;
//		} else {
//			throw new NoSuchIdFoundException("customer name is not found");
//		}
//	}
//	
//	public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(Long phone) {
//		Customer customer=customerDao.getCustomerByPhone(phone);
//		if (customer != null) {
//			ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
//			ResponseEntity<ResponseStructure<Customer>> entity = new ResponseEntity<>(responseStructure, HttpStatus.OK);
//			responseStructure.setStatuscode(HttpStatus.OK.value());
//			responseStructure.setMessage("customer details fetched successfully based on phone number");
//			responseStructure.setData(customer);
//			return entity;
//		} else {
//			throw new NoSuchIdFoundException("customer phone number is not found");
//		}
//	}
	
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer() {
		ResponseStructure<List<Customer>> responseStructure=new ResponseStructure<>();
		ResponseEntity<ResponseStructure<List<Customer>>> entity=new ResponseEntity<>(responseStructure,HttpStatus.OK);
		responseStructure.setStatuscode(HttpStatus.OK.value());
		responseStructure.setMessage("All customer details fetched successfully");
		responseStructure.setData(customerDao.getAllCustomer());
		return entity;
	}

	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(int id, Customer customer) {
		Customer customer2 = customerDao.getCustomerById(id);

		if (customer2 != null) {
			customer2.setCustomerId(id);
			ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
			ResponseEntity<ResponseStructure<Customer>> entity = new ResponseEntity<>(responseStructure, HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("customer details updated successfully");
			responseStructure.setData(customerDao.updateCustomer(customer));
			return entity;
		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteCustomer(int id) {
		Customer customer = customerDao.getCustomerById(id);
		if (customer != null) {
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			ResponseEntity<ResponseStructure<String>> entity = new ResponseEntity<>(responseStructure,
					HttpStatus.NO_CONTENT);
			responseStructure.setStatuscode(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("customer details deleted successfully");
			responseStructure.setData(customerDao.deleteCustomer(id));
			return entity;
		} else {
			throw new NoSuchIdFoundException("customer id is not found to delete");
		}

	}

}
