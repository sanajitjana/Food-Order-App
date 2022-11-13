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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.CategoryException;
import com.foodyexpress.exception.LoginException;
import com.foodyexpress.model.Category;
import com.foodyexpress.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService catService;

	@PostMapping("/add")
	public ResponseEntity<Category> addCategory(@RequestParam(required = false) String key,
			@RequestParam String categoryName) throws CategoryException, LoginException {
		Category newCategory = catService.addCategory(key, categoryName);
		return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Category> updateCategory(@RequestParam(required = false) String key,
			@RequestBody Category category) throws CategoryException, LoginException {
		Category updatedCategory = catService.updateCategory(key, category);
		return new ResponseEntity<Category>(updatedCategory, HttpStatus.ACCEPTED);
	}

	@GetMapping("/view")
	public ResponseEntity<Category> getCategoryById(@RequestParam(required = false) String key,
			@RequestParam Integer categoryId) throws CategoryException, LoginException {
		Category category = catService.viewCategoryById(key, categoryId);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@DeleteMapping("/remove")
	public ResponseEntity<Category> removeCategory(@RequestParam(required = false) String key,
			@RequestParam String categoryName) throws CategoryException, LoginException {
		Category removedCategory = catService.removeCategory(key, categoryName);
		return new ResponseEntity<Category>(removedCategory, HttpStatus.OK);
	}

	@GetMapping("/viewall")
	public ResponseEntity<List<Category>> getAllCategories(@RequestParam(required = false) String key)
			throws CategoryException, LoginException {
		List<Category> categories = catService.viewAllCategory(key);
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

}
