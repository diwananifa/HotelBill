package com.hotel.bill.dto;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String customerName;
	private Long customerPhoneNo;
	private double totalBillAmount;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Product> products;

}
