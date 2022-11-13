package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.LoginException;
import com.foodyexpress.model.Customer;

public interface CustomerService {

	Customer addCustomer(Customer customer) throws CustomerException;

	Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException;

	Customer removeCustomerById(String key, Integer customerId) throws CustomerException, LoginException;

	Customer removeCustomer(String key, Customer customer) throws CustomerException, LoginException;

	Customer viewCustomer(String key, Integer customerId) throws CustomerException, LoginException;

	List<Customer> viewAllCustomers(String key) throws CustomerException, LoginException;

}
