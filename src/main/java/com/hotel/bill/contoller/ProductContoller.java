package com.hotel.bill.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.bill.dto.Customer;
import com.hotel.bill.dto.Item;
import com.hotel.bill.dto.Product;
import com.hotel.bill.exception.NoSuchDataFoundException;
import com.hotel.bill.exception.NoSuchIdFoundException;
import com.hotel.bill.service.ProductService;
import com.hotel.bill.util.ResponseStructure;

@RestController
public class ProductContoller {
	@Autowired
	ProductService productService;
	@PostMapping("saveproduct")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestParam int customerId,@RequestParam int itemId,@RequestParam int qty) {
		return productService.saveProduct(customerId, itemId, qty);
	}
	@GetMapping("getproductbyid")
	public ResponseEntity<ResponseStructure<Product>> getProductById(@RequestParam int id) {
		return productService.getProductById(id);
	}
	@GetMapping("getproductbyname")
	public ResponseEntity<ResponseStructure<Product>> getProductByName(@RequestParam String name) {
		return productService.getProductByName(name);
	}
	@GetMapping("getallproduct")
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(){
		return productService.getAllProduct();
	}
	@DeleteMapping("deleteproductbyid")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@RequestParam int id) {
		return productService.deleteProduct(id);
	}
	@PutMapping("updateproductbyid")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestParam int id,@RequestParam int qty) {
		return productService.updateProduct(id, qty);
	}
}
