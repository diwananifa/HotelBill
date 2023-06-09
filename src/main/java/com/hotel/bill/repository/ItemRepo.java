package com.hotel.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.bill.dto.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {
	public Item findByItemName(String itemName);
}
