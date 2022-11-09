package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.model.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(Customer customer) throws CustomerException;

	public Customer removeCustomer(Integer customerId) throws CustomerException;

	public Customer viewCustomer(Integer customerId) throws CustomerException;

	public Customer viewCustomerById(Integer customerId) throws CustomerException;

	public List<Customer> viewAllCustomer() throws CustomerException;
}
