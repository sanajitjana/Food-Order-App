package com.foodyexpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) throws CustomerException {
		Customer customer = customerService.addCustomer(c);
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c) throws CustomerException {
		Customer customer = customerService.updateCustomer(c);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable("id") Integer customerId) throws CustomerException {
		Customer customer = customerService.removeCustomerById(customerId);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Customer> deleteCustomer(@RequestBody Customer c) throws CustomerException {
		Customer customer = customerService.removeCustomer(c);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping("/all/{id}")
	public ResponseEntity<Customer> viewCutomer(@PathVariable("id") Integer id) throws CustomerException {
		Customer customer = customerService.viewCustomer(id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Customer>> viewAllCustomers() throws CustomerException {
		List<Customer> customers = customerService.viewAllCustomers();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
}
