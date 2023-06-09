package com.hotel.bill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.bill.dao.MenuDao;
import com.hotel.bill.dto.Item;
import com.hotel.bill.dto.Menu;
import com.hotel.bill.exception.NoSuchDataFoundException;
import com.hotel.bill.exception.NoSuchIdFoundException;
import com.hotel.bill.util.ResponseStructure;

@Service
public class MenuService {

	@Autowired
	MenuDao menuDao;

	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu) {

		// 1.status--->200 OK(get put(update)) 201 created(save the data) 204 no
		// content(delete) 404 (not_found)-->these are the status which is found in
		// postman
		// 2.message
		// 3.object returning process
		List<Item> items = menu.getItems();
		if (items != null && !items.isEmpty()) {
			for (Item item : items) {
				item.setMenu(menu);
			}
		}
		menu.setItems(items);
		ResponseStructure<Menu> responseStructure = new ResponseStructure();

		// response entity is used to create our own status code in our frontend

		ResponseEntity<ResponseStructure<Menu>> entity = new ResponseEntity<ResponseStructure<Menu>>(responseStructure,
				HttpStatus.CREATED);

		responseStructure.setStatuscode(HttpStatus.CREATED.value());

		responseStructure.setMessage("menu details is saved sucessfully");

		responseStructure.setData(menuDao.saveMenu(menu));

		return entity;
	}

	public ResponseEntity<ResponseStructure<Menu>> findMenuById(int id) {

		ResponseStructure<Menu> responseStructure = new ResponseStructure();
		Menu menu = menuDao.findMenuById(id);
		if (menu != null) {

			ResponseEntity<ResponseStructure<Menu>> entity = new ResponseEntity<ResponseStructure<Menu>>(
					responseStructure, HttpStatus.OK);

			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("menu details is fetched sucessfully");
			responseStructure.setData(menu);

			return entity;
		} else {
			throw new NoSuchIdFoundException("menu id is not found ");

		}

	}

	public ResponseEntity<ResponseStructure<List<Menu>>> getAllMenu() {

		ResponseStructure<List<Menu>> responseStructure = new ResponseStructure();
		List<Menu> menus = menuDao.getAllMenu();
		if (!menus.isEmpty()) {
			ResponseEntity<ResponseStructure<List<Menu>>> entity = new ResponseEntity<ResponseStructure<List<Menu>>>(
					responseStructure, HttpStatus.OK);

			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("All menu details is fetched sucessfully");
			responseStructure.setData(menus);

			return entity;
		} else {
			throw new NoSuchDataFoundException("Menu detail is empty");
		}

	}

	public ResponseEntity<ResponseStructure<String>> deleteMenuByid(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure();
		Menu menu = menuDao.findMenuById(id);
		if (menu != null) {

			ResponseEntity<ResponseStructure<String>> entity = new ResponseEntity<ResponseStructure<String>>(
					responseStructure, HttpStatus.NO_CONTENT);

			responseStructure.setStatuscode(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("menu details deleted by sucessfully");
			responseStructure.setData(menuDao.deleteMenuByid(id));

			return entity;

		} else {
			throw new NoSuchIdFoundException("menu id is not found ");

		}

	}

	public ResponseEntity<ResponseStructure<Menu>> updateMenu(int id, Menu menu) {

		ResponseStructure<Menu> responseStructure = new ResponseStructure();
		Menu menu2 = menuDao.findMenuById(id);
		if (menu2 != null) {
			menu.setMenuId(id);
			List<Item> items = menu.getItems();
			if (items != null && !items.isEmpty()) {
				for (Item item : items) {
					item.setMenu(menu);
				}
			}
			menu.setItems(items);
			ResponseEntity<ResponseStructure<Menu>> entity = new ResponseEntity<ResponseStructure<Menu>>(
					responseStructure, HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("menu details updated sucessfully");
			responseStructure.setData(menuDao.updateMenu(menu));
			return entity;
		} else {
			throw new NoSuchIdFoundException("menu id is not found");
		}
	}
}
