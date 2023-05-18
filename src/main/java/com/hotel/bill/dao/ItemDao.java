package com.hotel.bill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.bill.dto.Item;
import com.hotel.bill.repository.ItemRepo;

@Repository
public class ItemDao {
	@Autowired
	ItemRepo itemRepo;
	public Item saveItem(Item item) {
		return itemRepo.save(item);
	}
	public Item getItemById(int id) {
		Optional<Item> optional=itemRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	public Item getItemByName(String name) {
		return itemRepo.findByName(name);
	}
	public List<Item> getAllItem(){
		return itemRepo.findAll();
	}
	public String deleteItem(int id) {
		itemRepo.deleteById(id);
		return "deleted";
	}
	public Item updateItem(Item item) {
		return itemRepo.save(item);
	}
}
