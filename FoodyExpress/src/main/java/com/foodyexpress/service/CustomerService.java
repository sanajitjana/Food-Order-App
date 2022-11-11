package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.model.Customer;

public interface CustomerService {

	Customer addCustomer(Customer c) throws CustomerException;

	Customer updateCustomer(Customer c) throws CustomerException;

	Customer removeCustomer(Customer c) throws CustomerException;

	Customer viewCustomer(Integer cid) throws CustomerException;

	List<Customer> viewAllCustomers() throws CustomerException;

}
