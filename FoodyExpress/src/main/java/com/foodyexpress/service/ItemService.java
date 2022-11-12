package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.CategoryException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.Category;
import com.foodyexpress.model.Item;

public interface ItemService {
	
	public Item addItem(Item item) throws ItemException;
	
	public Item updateItem(Item item) throws ItemException;
	
	public Item removeItem(Item item) throws ItemException;
	
	public Item removeItemById(Integer itemId) throws ItemException;
	
	public List<Item> getAllItem() throws ItemException;
	
	public List<Item> getAllItemByCategory(Category category) throws ItemException,CategoryException;
	
	public List<Item> getAllItemByCategoryName(String categoryName) throws ItemException,CategoryException;

}
