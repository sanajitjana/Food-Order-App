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
import com.foodyexpress.model.Category;
import com.foodyexpress.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService catService;

	@PostMapping("/add")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) throws CategoryException {
		Category newCategory = catService.addCategory(category);
		return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws CategoryException {
		Category updatedCategory = catService.updateCategory(category);
		return new ResponseEntity<Category>(updatedCategory, HttpStatus.ACCEPTED);
	}

	@GetMapping("/view/{categoryId}")
	public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Integer categoryId)
			throws CategoryException {
		Category category = catService.viewCategoryById(categoryId);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@DeleteMapping("/remove/{categoryId}")
	public ResponseEntity<Category> removeCategory(@PathVariable("categoryId") Integer categoryId)
			throws CategoryException {
		Category removedCategory = catService.removeCategoryById(categoryId);
		return new ResponseEntity<Category>(removedCategory, HttpStatus.OK);
	}

	@GetMapping("/viewall")
	public ResponseEntity<List<Category>> getAllCategories() throws CategoryException {
		List<Category> categories = catService.viewAllCategory();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

}
