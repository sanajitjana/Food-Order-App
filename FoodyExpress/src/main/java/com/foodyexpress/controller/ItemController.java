package com.foodyexpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.CategoryException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.Category;
import com.foodyexpress.model.Item;
import com.foodyexpress.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/all")
	public ResponseEntity<List<Item>> getAllItem() throws ItemException {
		List<Item> itemList = itemService.getAllItem();
		return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
	}

	@GetMapping("/get/category")
	public ResponseEntity<List<Item>> getAllItemByCategory(@RequestBody Category category)
			throws ItemException, CategoryException {
		List<Item> itemList = itemService.getAllItemByCategory(category);
		return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
	}

	@GetMapping("/get/{categoryName}")
	public ResponseEntity<List<Item>> getAllItemByCategoryName(@PathVariable String categoryName)
			throws ItemException, CategoryException {
		List<Item> itemList = itemService.getAllItemByCategoryName(categoryName);
		return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Item> addItem(@RequestBody Item item) throws ItemException {
		Item savedItem = itemService.addItem(item);

		return new ResponseEntity<Item>(savedItem, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Item> updateItem(@RequestBody Item item) throws ItemException {
		Item updatedItem = itemService.updateItem(item);

		return new ResponseEntity<Item>(updatedItem, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Item> removeItem(@RequestBody Item item) throws ItemException {
		Item deletedItem = itemService.removeItem(item);

		return new ResponseEntity<Item>(deletedItem, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Item> removeItemById(@PathVariable("id") Integer id) throws ItemException {
		Item deletedItem = itemService.removeItemById(id);

		return new ResponseEntity<Item>(deletedItem, HttpStatus.OK);
	}
}
