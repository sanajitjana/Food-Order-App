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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.LoginException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.Login;
import com.foodyexpress.service.CustomerService;
import com.foodyexpress.service.LoginService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws CustomerException {
		Customer returnCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(returnCustomer, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestParam(required = false) String key,
			@RequestBody Customer customer) throws CustomerException, LoginException {
		Customer returnCustomer = customerService.updateCustomer(key, customer);
		return new ResponseEntity<Customer>(returnCustomer, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Customer> deleteCustomerById(@RequestParam(required = false) String key,
			@PathVariable("id") Integer customerId) throws CustomerException, LoginException {
		Customer customer = customerService.removeCustomerById(key, customerId);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Customer> deleteCustomer(@RequestParam(required = false) String key,
			@RequestBody Customer customer) throws CustomerException, LoginException {
		Customer resCustomer = customerService.removeCustomer(key, customer);
		return new ResponseEntity<Customer>(resCustomer, HttpStatus.OK);
	}

	@GetMapping("/all/{id}")
	public ResponseEntity<Customer> viewCutomer(@RequestParam(required = false) String key,
			@PathVariable("id") Integer id) throws CustomerException, LoginException {
		Customer customer = customerService.viewCustomer(key, id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Customer>> viewAllCustomers(@RequestParam(required = false) String key)
			throws CustomerException, LoginException {
		List<Customer> customers = customerService.viewAllCustomers(key);
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
}
