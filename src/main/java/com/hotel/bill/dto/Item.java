package com.hotel.bill.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;
	private String itemName;
	private double itemPrice;
	
	@ManyToOne
	@JoinColumn
	@JsonBackReference
	private Menu menu;
	
}
