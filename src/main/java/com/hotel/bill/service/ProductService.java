package com.hotel.bill.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.bill.dao.CustomerDao;
import com.hotel.bill.dao.ItemDao;
import com.hotel.bill.dao.ProductDao;
import com.hotel.bill.dto.Customer;
import com.hotel.bill.dto.Item;
import com.hotel.bill.dto.Product;
import com.hotel.bill.exception.NoSuchDataFoundException;
import com.hotel.bill.exception.NoSuchIdFoundException;
import com.hotel.bill.util.ResponseStructure;

@Service
public class ProductService {
	@Autowired
	ProductDao productDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	ItemDao itemDao;
	public ResponseEntity<ResponseStructure<Product>> saveProduct(int c_id,int i_id,int qty) {
		Customer customer=customerDao.getCustomerById(c_id);
		Item item=itemDao.getItemById(i_id);
		if (customer!=null && item!=null) {
			Product product=new Product();
			product.setP_name(item.getI_name());
			product.setP_price(item.getI_price());
			product.setP_totalPrice(qty*item.getI_price());
			product=productDao.saveProduct(product);
			List<Product> products=customer.getProducts();
			if (products!=null) {
				products=new ArrayList<>();
			}
			products.add(product);
			customer.setTotalBillAmount(customer.getTotalBillAmount()+(qty*item.getI_price()));
			customer.setProducts(products);
			customerDao.updateCustomer(customer);
			ResponseStructure<Product> responseStructure=new ResponseStructure();
			ResponseEntity<ResponseStructure<Product>> entity=new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.CREATED);
			responseStructure.setStatuscode(HttpStatus.CREATED.value());
			responseStructure.setMessage("product object saved sucessfully");
			responseStructure.setData(product);
			return entity;
		} else if(customer==null) {
			throw new NoSuchIdFoundException("customer id not found");
		}else {
			throw new NoSuchIdFoundException("item id not found");
		}
	}
	public ResponseEntity<ResponseStructure<Product>> getProductById(int id) {
		Product product=productDao.getProductById(id);
		if (product!=null) {
			ResponseStructure<Product> responseStructure=new ResponseStructure();
			ResponseEntity<ResponseStructure<Product>> entity=new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("product details fetched sucessfully");
			responseStructure.setData(product);
			return entity;
		} else {
			throw new NoSuchIdFoundException("product id not found");
		}
	}
	public ResponseEntity<ResponseStructure<Product>> getProductByName(String name) {
		Product product=productDao.getProductByName(name);
		if (product!=null) {
			ResponseStructure<Product> responseStructure=new ResponseStructure();
			ResponseEntity<ResponseStructure<Product>> entity=new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("product object saved sucessfully");
			responseStructure.setData(product);
			return entity;
		} else {
			throw new NoSuchIdFoundException("product details fetched sucessfully");
		}
	}
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(){
		List<Product> products=productDao.getAllProduct();
		if (!products.isEmpty()) {
			ResponseStructure<List<Product>> responseStructure=new ResponseStructure();
			ResponseEntity<ResponseStructure<List<Product>>> entity=new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure,HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("All product details fetched sucessfully");
			responseStructure.setData(products);
			return entity;
		} else {
			throw new NoSuchDataFoundException("product id not found");
		}
	}
	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
		Product product=productDao.getProductById(id);
		if(product!=null) {
			Customer customer= product.getCustomer();
			customer.setTotalBillAmount(customer.getTotalBillAmount()-product.getP_totalPrice());
			customerDao.updateCustomer(customer);
			ResponseStructure<String> responseStructure=new ResponseStructure();
			ResponseEntity<ResponseStructure<String>> entity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NO_CONTENT);
			responseStructure.setStatuscode(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("product details deleted sucessfully");
			responseStructure.setData(productDao.deleteProduct(id));
			return entity;
		}
		else {
			throw new NoSuchIdFoundException("product id not found");
		}
	}
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int id,int qty) {
		Product product=productDao.getProductById(id);
		if(product!=null) {
			double new_totalPrice=(qty*product.getP_price())-product.getP_totalPrice();
			product.setQty(qty);
			product.setP_totalPrice(qty*product.getP_price());
			Customer customer=product.getCustomer();
			customer.setTotalBillAmount(customer.getTotalBillAmount()+new_totalPrice);
			customerDao.updateCustomer(customer);
			ResponseStructure<Product> responseStructure=new ResponseStructure();
			ResponseEntity<ResponseStructure<Product>> entity=new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.OK);
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("product details updated sucessfully");
			responseStructure.setData(product);
			return entity;
		}else {
			throw new NoSuchIdFoundException("product id not found");
		}
	}
}
