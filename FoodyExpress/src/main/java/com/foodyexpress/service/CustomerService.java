package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.model.Customer;

public interface CustomerService {

	Customer addCustomer(Customer customer) throws CustomerException;

	Customer updateCustomer(Customer customer) throws CustomerException;

	Customer removeCustomerById(Integer customerId) throws CustomerException;

	Customer removeCustomer(Customer customer) throws CustomerException;

	Customer viewCustomer(Integer customerId) throws CustomerException;

	List<Customer> viewAllCustomers() throws CustomerException;

}
