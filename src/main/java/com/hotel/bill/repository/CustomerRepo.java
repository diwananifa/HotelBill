package com.hotel.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.bill.dto.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
