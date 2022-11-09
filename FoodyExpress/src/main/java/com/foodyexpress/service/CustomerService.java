package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.Restaurant;

public interface CustomerService {

	Customer addCustomer(Customer c) throws CustomerException;

	Customer updateCustomer(Customer c) throws CustomerException;

	Customer removeCustomer(Customer c) throws CustomerException;

	Customer viewCustomer(Customer c) throws CustomerException;

	List<Customer> viewAllCustomers(Restaurant res) throws CustomerException;

}
