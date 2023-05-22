package com.hotel.bill.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.bill.dao.ItemDao;
import com.hotel.bill.dao.MenuDao;
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
	
	@Autowired
	MenuDao menuDao;
	public ResponseEntity<ResponseStructure<Item>> saveItem(int id,Item item) {
		Menu menu=menuDao.findMenuById(id);
		if(menu!=null) {
			item.setMenu(menu);
			List<Item> items=menu.getItems();
			if(items==null) {
				items=new ArrayList<>();
			}
			items.add(item);
			menu.setItems(items);
			menuDao.updateMenu(menu);
			List<Item> items1=menu.getItems();
			Item item1=items1.get(items.size()-1);
			ResponseStructure<Item> responseStructure=new ResponseStructure<>();
			ResponseEntity<ResponseStructure<Item>> entity=new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.CREATED);
			responseStructure.setStatuscode(HttpStatus.CREATED.value());
			responseStructure.setMessage("item details is saved sucessfully");
			responseStructure.setData(item1);
			return entity;
		}
		else {
			throw new NoSuchIdFoundException("menu id is not found to save item");
		}
		
	}
	public ResponseEntity<ResponseStructure<Item>> getItemById(int id) {
		ResponseStructure<Item> responseStructure=new ResponseStructure<>();
		Item item=itemDao.getItemById(id);
		if(item!=null) {
			ResponseEntity<ResponseStructure<Item>> entity=new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("item details fetched sucessfully based on id");
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
			responseStructure.setMessage("item details fetched sucessfully based on name");
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
			responseStructure.setMessage("All item details fetched sucessfully");
			responseStructure.setData(items);
			return entity;
		}
		else {
			throw new NoSuchDataFoundException("item detail is empty");
		}
	}
	public ResponseEntity<ResponseStructure<String>> deleteItem(int id) {
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		Item item=itemDao.getItemById(id);
		if(item!=null) {
			ResponseEntity<ResponseStructure<String>> entity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NO_CONTENT);
			responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("item details deleted sucessfully");
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
			item.setItemId(id);
			if(item.getMenu()==null) {
				Menu menu=item1.getMenu();
				item.setMenu(menu);
			}
			ResponseEntity<ResponseStructure<Item>> entity=new ResponseEntity<ResponseStructure<Item>>(responseStructure,HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("item details updated sucessfully");
			responseStructure.setData(itemDao.updateItem(item));
			return entity;
		}
		else {
			throw new NoSuchIdFoundException("item id is not found");
		}
	}
}
