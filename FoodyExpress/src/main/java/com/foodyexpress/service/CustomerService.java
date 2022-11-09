package com.foodyexpress.service;

import java.lang.module.ResolutionException;
import java.util.List;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.Restaurant;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(Customer customer) throws CustomerException;

	public Customer removeCustomer(Integer customerId) throws CustomerException;

	public Customer viewCustomer(Integer customerId) throws CustomerException;

	public List<Customer> viewAllCustomer(Customer customer) throws CustomerException;

	public List<Customer> viewAllCustomerOfRestaurant(Restaurant restaurant)
			throws CustomerException, ResolutionException;
}
