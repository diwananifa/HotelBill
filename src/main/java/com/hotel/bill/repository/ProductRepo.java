package com.hotel.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.bill.dto.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
