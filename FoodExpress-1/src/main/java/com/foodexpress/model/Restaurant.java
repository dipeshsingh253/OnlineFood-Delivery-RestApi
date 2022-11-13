package com.foodexpress.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	@Id
	private Integer restaurantId;
	private String restaurantName;

	@Embedded
	private Address address;
	
	@JsonIgnore
	@ManyToMany
//	@JoinTable(name = "restaurant_item",joinColumns = @JoinColumn(name = "restaurant_id"),inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Item> itemList = new ArrayList<>();
	
	
	private String managerName;
	private String contactNumber;
}
