package com.foodyexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodyexpress.model.Bill;

public interface BillDao extends JpaRepository<Bill, Integer>{

}


