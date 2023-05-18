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
import com.hotel.bill.dto.Customer;
import com.hotel.bill.service.CustomerService;
import com.hotel.bill.util.ResponseStructure;

@RestController
public class CustomerContoller {

	@Autowired
	CustomerService customerService;

	@PostMapping("savecustomer")
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}

	@GetMapping("getcustomerbyid")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@RequestParam int id) {
		return customerService.getCustomerById(id);
	}

//	@GetMapping("getcustomerbyname")
//	public ResponseEntity<ResponseStructure<Customer>> getCustomerByName(@RequestParam String name) {
//		return customerService.getCustomerByName(name);
//	}
//
//	@GetMapping("getcustomerbyphone")
//	public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(@RequestParam Long phone) {
//		return customerService.getCustomerByPhone(phone);
//	}

	@GetMapping("getallcustomer")
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer() {
		return customerService.getAllCustomer();
	}

	@PutMapping("updatecustomer")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestParam int id,
			@RequestBody Customer customer) {
		return customerService.updateCustomer(id, customer);
	}

	@DeleteMapping("deletecustomer")
	public ResponseEntity<ResponseStructure<String>> deleteCustomer(@RequestParam int id) {
		return customerService.deleteCustomer(id);
	}

}
