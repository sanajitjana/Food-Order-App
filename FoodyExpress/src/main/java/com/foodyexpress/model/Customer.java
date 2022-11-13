package com.foodyexpress.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@NotNull(message = "First name is mandatory")
	private String firstName;
	
	@NotNull(message = "Last name is mandatory")
	private String lastName;
	
	private Integer age;
	private String gender;
	
	@Size(max = 10,min = 10, message = "Require only 10 digit")
	private String mobileNumber;
	
	@NotNull(message = "Email is mandatory")
	@Email(message = "Require email format")
	private String email;
	
	@NotNull(message = "Password is mandatory")
	private String password;

	@JsonIgnore
	@OneToOne(targetEntity = FoodCart.class)
	private FoodCart cart;

}