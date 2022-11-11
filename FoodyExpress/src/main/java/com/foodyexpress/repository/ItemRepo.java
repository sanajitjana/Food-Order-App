package com.foodyexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer>{
	
	public Item findByItemName(String item) throws ItemException;
	
	
}
