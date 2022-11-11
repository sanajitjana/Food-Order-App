package com.foodyexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodyexpress.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
