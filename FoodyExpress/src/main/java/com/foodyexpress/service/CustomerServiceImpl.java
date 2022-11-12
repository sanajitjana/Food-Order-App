package com.foodyexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.FoodCart;
import com.foodyexpress.repository.CustomerRepo;
import com.foodyexpress.repository.FoodCartRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private FoodCartRepo foodCartRepo;

	@Override
	public Customer addCustomer(Customer c) throws CustomerException {

		Customer customer = customerRepo.findByEmail(c.getEmail());

		if (customer != null) {
			throw new CustomerException("Customer email alreday exists!");
		} else {
			
			FoodCart foodCart=new FoodCart();
			c.setCart(foodCart);
			foodCart.setCustomer(c);
			foodCartRepo.save(foodCart);
			
			return customerRepo.save(c);
		}

	}

	@Override
	public Customer updateCustomer(Customer c) throws CustomerException {

		if (c == null) {
			throw new CustomerException("Null value is not allowed");
		}

		Optional<Customer> optional = customerRepo.findById(c.getCustomerId());

		if (optional.isEmpty()) {
			throw new CustomerException("No customer exist with given customer id :" + c.getCustomerId());
		}
		// this statement will update customer and overrides the old customer data
		return customerRepo.save(c);
	}

	@Override
	public Customer removeCustomerById(Integer customerId) throws CustomerException {

		Optional<Customer> optional = customerRepo.findById(customerId);

		if (optional.isEmpty()) {
			throw new CustomerException("No customer exist with given customer id :" + customerId);
		}

		Customer deletedCustomer = optional.get();
		customerRepo.delete(deletedCustomer);
		return deletedCustomer;
	}

	@Override
	public Customer removeCustomer(Customer c) throws CustomerException {
		if (c == null) {
			throw new CustomerException("Null value is not allowed");
		}

		Optional<Customer> optional = customerRepo.findById(c.getCustomerId());

		if (optional.isEmpty()) {
			throw new CustomerException("No customer exist with given customer id :" + c.getCustomerId());
		}

		Customer deletedCustomer = optional.get();
		customerRepo.delete(deletedCustomer);
		return deletedCustomer;
	}

	@Override
	public Customer viewCustomer(Integer cid) throws CustomerException {

		Optional<Customer> optional = customerRepo.findById(cid);

		if (optional.isEmpty()) {
			throw new CustomerException("No customer exist with given customer id :" + cid);
		}

		return optional.get();
	}

	@Override
	public List<Customer> viewAllCustomers() throws CustomerException {

		List<Customer> customers = customerRepo.findAll();

		if (customers.size() == 0) {
			throw new CustomerException("No customers available in list");
		}

		return customers;
	}

}
