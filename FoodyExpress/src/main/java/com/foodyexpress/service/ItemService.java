package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.Item;

public interface ItemService {
	
	public Item addItem(Item item) throws ItemException;
	
	public Item updateItem(Item item) throws ItemException;
	
	public Item removeItem(Item item) throws ItemException;
	
	public Item removeItem(Integer itemId) throws ItemException;
	
	public List<Item> getAllItem() throws ItemException;

}
