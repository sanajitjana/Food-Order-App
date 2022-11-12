package com.foodyexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.CategoryException;
import com.foodyexpress.model.Category;
import com.foodyexpress.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category addCategory(Category category) throws CategoryException {
		// TODO Auto-generated method stub

		Optional<Category> opt = categoryRepo.findById(category.getCategoryId());
		if (opt.isPresent()) {
			throw new CategoryException("Category already exists!");
		} else {
			return categoryRepo.save(category);
		}
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
		// TODO Auto-generated method stub

		Optional<Category> opt = categoryRepo.findById(category.getCategoryId());
		if (opt.isPresent()) {
			return categoryRepo.save(category);
		} else {
			throw new CategoryException("Category id not found!");
		}
	}

	@Override
	public Category removeCategoryById(Integer categoryId) throws CategoryException {
		// TODO Auto-generated method stub

		Optional<Category> opt = categoryRepo.findById(categoryId);
		if (opt.isPresent()) {
			Category category = opt.get();
			categoryRepo.delete(category);
			return category;
		} else {
			throw new CategoryException("Category id not found!");
		}
	}

	@Override
	public List<Category> viewAllCategory() throws CategoryException {
		// TODO Auto-generated method stub

		List<Category> categories = categoryRepo.findAll();
		if (categories.size() > 0) {
			return categories;
		} else {
			throw new CategoryException("Categories list is empty!");
		}
	}

	@Override
	public Category viewCategoryById(Integer categoryId) throws CategoryException {
		// TODO Auto-generated method stub

		Optional<Category> opt = categoryRepo.findById(categoryId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new CategoryException("Category id not found!");
		}
	}

}
