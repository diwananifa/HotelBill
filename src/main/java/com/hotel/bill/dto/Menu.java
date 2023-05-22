package com.hotel.bill.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int menuId;
	@Column(unique = true)
	private String menuName;
	@OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
	List<Item> items;
}
