package com.foodyexpress.service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.Restaurant;
import com.foodyexpress.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo custRepo;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub

		Optional<Customer> opt = custRepo.findById(customer.getCustomerId());
		if (opt.isPresent()) {
			throw new CustomerException("Customer already exists!");
		} else {
			return custRepo.save(customer);
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub

		Optional<Customer> opt = custRepo.findById(customer.getCustomerId());
		if (opt.isPresent()) {
			return custRepo.save(customer);
		} else {
			throw new CustomerException("Customer id not found!");
		}
	}

	@Override
	public Customer removeCustomer(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub

		Optional<Customer> opt = custRepo.findById(customerId);
		if (opt.isPresent()) {
			Customer customer = opt.get();
			custRepo.delete(customer);
			return customer;
		} else {
			throw new CustomerException("Customer id not found!");
		}
	}

	@Override
	public List<Customer> viewAllCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> viewAllCustomerOfRestaurant(Restaurant restaurant)
			throws CustomerException, ResolutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
