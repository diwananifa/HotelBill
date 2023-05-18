package com.hotel.bill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.bill.dto.Product;
import com.hotel.bill.repository.ProductRepo;

@Repository
public class ProductDao {
	@Autowired
	ProductRepo productRepo;
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}
	public Product getProductById(int id) {
		Optional<Product> optional= productRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}
	public Product getProductByName(String name) {
		return productRepo.getProductByName(name);
//		if (product!=null) {
//			return product;
//		}
//		else {
//			return null;
//		}
	}
	public List<Product> getAllProduct(){
		return productRepo.findAll();
	}
	public String deleteProduct(int id) {
		productRepo.deleteById(id);
		return null;
	}
	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}
}
