package com.hotel.bill.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.bill.dao.ItemDao;
import com.hotel.bill.dto.Item;
import com.hotel.bill.exception.NoSuchDataFoundException;
import com.hotel.bill.exception.NoSuchIdFoundException;
import com.hotel.bill.exception.NoSuchNameFoundException;
import com.hotel.bill.service.ItemService;
import com.hotel.bill.util.ResponseStructure;

@RestController
public class ItemContoller {
	@Autowired
	ItemService itemService;
	@PostMapping("saveitem")
	public ResponseEntity<ResponseStructure<Item>> saveItem(@RequestParam int id, @RequestBody Item item) {
		return itemService.saveItem(id,item);
	}
	@GetMapping("getitembyid")
	public ResponseEntity<ResponseStructure<Item>> getItemById(@RequestParam int id) {
		return itemService.getItemById(id);
	}
	@GetMapping("getitembyname")
	public ResponseEntity<ResponseStructure<Item>> getItemByName(@RequestParam String name) {
		return itemService.getItemByName(name);
	}
	@GetMapping("getallitem")
	public ResponseEntity<ResponseStructure<List<Item>>> getAllItem(){
		return itemService.getAllItem();
	}
	@DeleteMapping("deleteitembyid")
	public ResponseEntity<ResponseStructure<String>> deleteItem(@RequestParam int id) {
		return itemService.deleteItem(id);
	}
	@PostMapping("updateitembyid")
	public ResponseEntity<ResponseStructure<Item>> updateItem(@RequestParam int id,@RequestBody Item item) {
		return itemService.updateItem(id, item);
	}
}
