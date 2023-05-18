package com.hotel.bill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.bill.dao.ItemDao;
import com.hotel.bill.dto.Item;
import com.hotel.bill.dto.Menu;
import com.hotel.bill.exception.NoSuchDataFoundException;
import com.hotel.bill.exception.NoSuchIdFoundException;
import com.hotel.bill.exception.NoSuchNameFoundException;
import com.hotel.bill.util.ResponseStructure;

@Service
public class ItemService {
	@Autowired
	ItemDao itemDao;
	public ResponseEntity<ResponseStructure<Item>> saveItem(Item item) {
		ResponseStructure<Item> responseStructure=new ResponseStructure<>();
		ResponseEntity<ResponseStructure<Item>> entity=new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.CREATED);
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("item is saved sucessfully");
		responseStructure.setData(itemDao.saveItem(item));
		return entity;
	}
	public ResponseEntity<ResponseStructure<Item>> getItemById(int id) {
		ResponseStructure<Item> responseStructure=new ResponseStructure<>();
		Item item=itemDao.getItemById(id);
		if(item!=null) {
			ResponseEntity<ResponseStructure<Item>> entity=new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("item object fetched sucessfully based on id");
			responseStructure.setData(item);
			return entity;
		}
		else {
			throw new NoSuchIdFoundException("item id is not found");
		}
	}
	public ResponseEntity<ResponseStructure<Item>> getItemByName(String name) {
		ResponseStructure<Item> responseStructure=new ResponseStructure<>();
		Item item=itemDao.getItemByName(name);
		if(item!=null) {
			ResponseEntity<ResponseStructure<Item>> entity=new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("item object fetched sucessfully based on name");
			responseStructure.setData(item);
			return entity;
		}
		else {
			throw new NoSuchNameFoundException("item name is not found");
		}
	}
	public ResponseEntity<ResponseStructure<List<Item>>> getAllItem(){
		ResponseStructure<List<Item>> responseStructure=new ResponseStructure<>();
		List<Item> items=itemDao.getAllItem();
		if(items!=null) {
			ResponseEntity<ResponseStructure<List<Item>>> entity=new ResponseEntity<ResponseStructure<List<Item>>>(responseStructure,HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("All item object fetched sucessfully");
			responseStructure.setData(items);
			return entity;
		}
		else {
			throw new NoSuchDataFoundException("data is not found");
		}
	}
	public ResponseEntity<ResponseStructure<String>> deleteItem(int id) {
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		Item item=itemDao.getItemById(id);
		if(item!=null) {
			ResponseEntity<ResponseStructure<String>> entity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NO_CONTENT);
			responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("item object deleted sucessfully");
			responseStructure.setData(itemDao.deleteItem(id));
			return entity;
		}
		else {
			throw new NoSuchIdFoundException("item id is not found");
		}
	}
	public ResponseEntity<ResponseStructure<Item>> updateItem(int id,Item item) {
		ResponseStructure<Item> responseStructure=new ResponseStructure<>();
		Item item1=itemDao.getItemById(id);
		if(item1!=null) {
			item.setI_id(id);
			ResponseEntity<ResponseStructure<Item>> entity=new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("item object  sucessfully");
			responseStructure.setData(itemDao.updateItem(item));
			return entity;
		}
		else {
			throw new NoSuchIdFoundException("item id is not found");
		}
	}
}
