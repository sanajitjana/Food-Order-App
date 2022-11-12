package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.CategoryException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.CategoryDTO;
import com.foodyexpress.model.Item;
import com.foodyexpress.model.ItemDTO;

public interface ItemService {
	
	public Item addItem(Item item) throws ItemException, CategoryException;
	
	public Item updateItem(ItemDTO itemDTO) throws ItemException, CategoryException;
	
	public Item removeItem(ItemDTO itemDTO) throws ItemException;
	
	public Item removeItemById(Integer itemId) throws ItemException;
	
	public List<Item> getAllItem() throws ItemException;
	
	public List<Item> getAllItemByCategory(CategoryDTO categoryDTO) throws ItemException,CategoryException;
	
	public List<Item> getAllItemByCategoryName(String categoryName) throws ItemException,CategoryException;

}
