package com.foodyexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.CategoryException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.Category;
import com.foodyexpress.model.Item;
import com.foodyexpress.repository.CategoryRepo;
import com.foodyexpress.repository.ItemRepo;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Item addItem(Item item) throws ItemException{
		Item existedItem=itemRepo.findByItemName(item.getItemName());
		if(existedItem!=null)
		{
			throw new ItemException("Item already exists...!");
		}
		else
		{
			Category category=item.getCategory();
			
			category.getItemList().add(item);
		    return itemRepo.save(item);	
		}
	}

	@Override
	public Item updateItem(Item item) throws ItemException {
		Optional<Item> opt=itemRepo.findById(item.getItemId());
		if(opt.isPresent())
		{
			Item updatedItem=itemRepo.save(item);
			return updatedItem;
		}
		else
		{
			throw new ItemException("Item does not exist..!");
		}
	}

	@Override
	public Item removeItem(Item item) throws ItemException {
		Optional<Item> opt=itemRepo.findById(item.getItemId());
		if(opt.isPresent())
		{
			Item deletedItem=opt.get();
			itemRepo.delete(deletedItem);
			return deletedItem;
		}
		else
		{
			throw new ItemException("Item does not exist..!");
		}
		
	}

	@Override
	public Item removeItemById(Integer itemId) throws ItemException {
		Optional<Item> opt=itemRepo.findById(itemId);
		if(opt.isPresent())
		{
			Item deletedItem=opt.get();
			itemRepo.delete(deletedItem);
			return deletedItem;
		}
		else
		{
			throw new ItemException("Item with id "+itemId+" does not exist..!");
		}
	}

	@Override
	public List<Item> getAllItem() throws ItemException {
		List<Item> itemList=itemRepo.findAll();
		if(itemList.isEmpty())
		{
			throw new ItemException("no item found..!");
		}
		else
		{
		return itemList;
		}
	}

	@Override
	public List<Item> getAllItemByCategory(Category category) throws ItemException, CategoryException {
		Optional<Category> opt=categoryRepo.findById(category.getCatId());
		if(opt.isPresent())
		{
			Category existedCategory=opt.get();
			List<Item> itemList=existedCategory.getItemList();
			if(itemList.isEmpty())
			{
				throw new ItemException("no item found in this category...!");
			}
			else
			{
				return itemList;
			}
		}
		else
		{
			throw new CategoryException("category does not exist..!");
		}
	}

	@Override
	public List<Item> getAllItemByCategoryName(String categoryName) throws ItemException, CategoryException {
		Category category=categoryRepo.findByCategoryName(categoryName);
		if(category!=null)
		{
			List<Item> itemList=category.getItemList();
			if(itemList.isEmpty())
			{
				throw new ItemException("no item found in this category...!");
			}
			else
			{
				return itemList;
			}
		}
		else
		{
			throw new CategoryException("category does not exist..!");
		}
	}

}
