package com.foodyexpress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.Item;
import com.foodyexpress.repository.ItemRepo;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepo itemRepo;

	@Override
	public Item addItem(Item item) throws ItemException {		
		return itemRepo.save(item);		
	}

	@Override
	public Item updateItem(Item item) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item removeItem(Item item) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item removeItem(Integer itemId) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getAllItem() throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

}
