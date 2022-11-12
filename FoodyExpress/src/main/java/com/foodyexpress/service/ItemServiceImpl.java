package com.foodyexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.CategoryException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.Category;
import com.foodyexpress.model.CategoryDTO;
import com.foodyexpress.model.Item;
import com.foodyexpress.model.ItemDTO;
import com.foodyexpress.repository.CategoryRepo;
import com.foodyexpress.repository.ItemRepo;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepo itemRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Item addItem(Item item) throws ItemException, CategoryException {

		Item existedItem = itemRepo.findByItemName(item.getItemName());
		if (existedItem == null) {

			Category exCategory = categoryRepo.findByCategoryName(item.getCategory().getCategoryName());
			if (exCategory != null) {
				exCategory.getItemList().add(item);
				item.setCategory(exCategory);
				return itemRepo.save(item);
			} else {
				throw new CategoryException("Category doesn't exists!");
			}
		} else {
			throw new ItemException("Item name already exits!");
		}
	}

	@Override
	public Item updateItem(ItemDTO itemDTO) throws ItemException, CategoryException {

		Optional<Item> opt = itemRepo.findById(itemDTO.getItemId());
		if (opt.isEmpty())
			throw new ItemException("Item not found!");

		Item item = opt.get();
		if (itemDTO.getCatergoryId() != null) {
			Optional<Category> cat = categoryRepo.findById(itemDTO.getCatergoryId());
			if (cat.isEmpty())
				throw new CategoryException("Category not found!");

			Category category = item.getCategory();
			category.getItemList().remove(item);

			item.setCategory(cat.get());
			cat.get().getItemList().add(item);
		}

		if (itemDTO.getCost() != null)
			item.setCost(itemDTO.getCost());

		if (itemDTO.getQuantity() != null)
			item.setQuantity(itemDTO.getQuantity());

		if (itemDTO.getItemName() != null)
			item.setItemName(itemDTO.getItemName());

		return itemRepo.save(item);

	}

	@Override
	public Item removeItem(ItemDTO itemDTO) throws ItemException {
		Optional<Item> opt = itemRepo.findById(itemDTO.getItemId());
		if (opt.isPresent()) {
			Item deletedItem = opt.get();
			itemRepo.delete(deletedItem);
			return deletedItem;
		} else {
			throw new ItemException("Item id not found..!");
		}

	}

	@Override
	public Item removeItemById(Integer itemId) throws ItemException {
		Optional<Item> opt = itemRepo.findById(itemId);
		if (opt.isPresent()) {
			Item deletedItem = opt.get();
			itemRepo.delete(deletedItem);
			return deletedItem;
		} else {
			throw new ItemException("Item id not found..!");
		}
	}

	@Override
	public List<Item> getAllItem() throws ItemException {
		List<Item> itemList = itemRepo.findAll();
		if (itemList.isEmpty()) {
			throw new ItemException("Empty item list..!");
		} else {
			return itemList;
		}
	}

	@Override
	public List<Item> getAllItemByCategory(CategoryDTO categoryDTO) throws ItemException, CategoryException {
		Category category = categoryRepo.findByCategoryName(categoryDTO.getCategoryName());
		if (category != null) {
			List<Item> itemList = category.getItemList();
			if (itemList.isEmpty()) {
				throw new ItemException("No item found in this category..!");
			} else {
				return itemList;
			}
		} else {
			throw new CategoryException("Category does not exist..!");
		}
	}

	@Override
	public List<Item> getAllItemByCategoryName(String categoryName) throws ItemException, CategoryException {
		Category category = categoryRepo.findByCategoryName(categoryName);
		if (category != null) {
			List<Item> itemList = category.getItemList();
			if (itemList.isEmpty()) {
				throw new ItemException("No item found in this category..!");
			} else {
				return itemList;
			}
		} else {
			throw new CategoryException("Category does not exist..!");
		}
	}
}
