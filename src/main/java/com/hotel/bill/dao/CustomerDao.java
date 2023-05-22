package com.hotel.bill.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hotel.bill.dto.Customer;
import com.hotel.bill.repository.CustomerRepo;

@Repository
public class CustomerDao {

	@Autowired
	CustomerRepo customerRepo;

	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public Customer getCustomerById(int id) {
		Optional<Customer> optional = customerRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Customer getCustomerByName(String name) {
		return customerRepo.findByCustomerName(name);
	}

	public Customer getCustomerByPhone(Long phone) {
		return customerRepo.findByCustomerPhoneNo(phone);
	}

	public List<Customer> getAllCustomer() {
		return customerRepo.findAll();
	}

	public Customer updateCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public String deleteCustomer(int id) {
		customerRepo.deleteById(id);
		return "Customer Deleted Successfully";
	}
}
