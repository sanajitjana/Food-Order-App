package com.foodyexpress.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurantId;

	@NotNull(message = "Name is require")
	private String restaurantName;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;

	@JsonIgnore
	@ManyToMany(targetEntity = Item.class, cascade = CascadeType.ALL, mappedBy = "restaurants")
	private List<Item> itemList = new ArrayList<>();
	private String managerName;

	@Size(min = 10, max = 10, message = "Mobile require only 10 digit")
	private String contactNunber;

}
