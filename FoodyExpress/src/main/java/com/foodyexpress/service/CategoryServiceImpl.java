package com.foodyexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.CategoryException;
import com.foodyexpress.exception.LoginException;
import com.foodyexpress.model.Category;
import com.foodyexpress.model.CurrentUserSession;
import com.foodyexpress.repository.CategoryRepo;
import com.foodyexpress.repository.CurrentUserSessionRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private CurrentUserSessionRepo currSession;

	@Override
	public Category addCategory(String key, String categoryName) throws CategoryException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			Category exixtsCatogory = categoryRepo.findByCategoryName(categoryName);
			if (exixtsCatogory != null)
				throw new CategoryException("Category already exists!");

			Category category = new Category();
			category.setCategoryName(categoryName);

			return categoryRepo.save(category);
		} else
			throw new LoginException("Admin login required");

	}

	@Override
	public Category updateCategory(String key, Category category) throws CategoryException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			Optional<Category> categoryOpt = categoryRepo.findById(category.getCategoryId());
			if (categoryOpt.isEmpty())
				throw new CategoryException("Category not found!");

			return categoryRepo.save(category);
		} else
			throw new LoginException("Admin login required");

	}

	@Override
	public Category removeCategory(String key, String categoryName) throws CategoryException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			Category category = categoryRepo.findByCategoryName(categoryName);
			if (category == null)
				throw new CategoryException("Category not found!");

			categoryRepo.delete(category);
			return category;
		} else
			throw new LoginException("Admin login required");

	}

	@Override
	public List<Category> viewAllCategory(String key) throws CategoryException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		List<Category> categories = categoryRepo.findAll();
		if (categories.isEmpty())
			throw new CategoryException("Categories list is empty!");

		return categories;
	}

	@Override
	public Category viewCategoryById(String key, Integer categoryId) throws CategoryException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Optional<Category> opt = categoryRepo.findById(categoryId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new CategoryException("Category id not found!");
		}
	}

}
