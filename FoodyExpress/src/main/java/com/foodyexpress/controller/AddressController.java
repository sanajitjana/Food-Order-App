package com.foodyexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.service.AddressService;

@RestController
public class AddressController {
	
//	@Autowired
//	private AddressService addressService;
//	
//	@PostMapping("/address")
//	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) throws CustomerException {
//		Customer customer = customerService.addCustomer(c);
//		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
//	}

}
