package com.hotel.bill.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.bill.dto.Menu;

import com.hotel.bill.service.MenuService;
import com.hotel.bill.util.ResponseStructure;

@RestController//it will control all package 
public class MenuContoller {

	@Autowired
	MenuService menuService;
	
	@PostMapping("savemenu")
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(@RequestBody Menu menu) {
		
	return menuService.saveMenu(menu);
 }
	@GetMapping("getmenubyid")
	public ResponseEntity<ResponseStructure<Menu>> findMenuById(@RequestParam int id) {
		
		return menuService.findMenuById(id);
	}
	@GetMapping("getallmenu")
	public ResponseEntity<ResponseStructure<List<Menu>>> getAllMenu() {
		
	     return	menuService.getAllMenu();
	}
	@DeleteMapping("deletemenubyid")
	public ResponseEntity<ResponseStructure<String>> deleteMenuByid(@RequestParam int id) {
		
		return menuService.deleteMenuByid(id);
	}
	@PutMapping("updatemenubyid")
	public ResponseEntity<ResponseStructure<Menu>> updateMenu(@RequestParam  int id ,@RequestBody  Menu menu) {
		
	    return menuService.updateMenu(id, menu) ;
	}
}
