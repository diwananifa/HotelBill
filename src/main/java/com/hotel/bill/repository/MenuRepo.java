package com.hotel.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.bill.dto.Menu;

public interface MenuRepo extends JpaRepository<Menu, Integer> {

}
