package com.hotel.bill.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.bill.dto.Menu;
import com.hotel.bill.repository.MenuRepo;

@Repository//---it will handle the database logics
public class MenuDao {

	@Autowired
	MenuRepo repo ;
	
	public Menu saveMenu(Menu menu) {
		
	return 	repo.save(menu);
 }
	
	public Menu findMenuById(int id) {
		
		Optional<Menu> optional = repo.findById(id);
		
		if (optional.isPresent()) {
			
			return optional.get();
		}
		else
			return null;
	}
	public List<Menu> getAllMenu() {
		
	     return	repo.findAll();
	}
	
	public String deleteMenuByid(int id) {
		repo.deleteById(id);
		return "entered menu id is deleted";
	}
	public Menu updateMenu(Menu menu) {
		
	    return	repo.save(menu);
	}
	
}
