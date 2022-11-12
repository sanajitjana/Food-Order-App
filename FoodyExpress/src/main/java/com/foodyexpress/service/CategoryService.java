package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.CategoryException;
import com.foodyexpress.model.Category;

public interface CategoryService {

	public Category addCategory(Category category) throws CategoryException;

	public Category updateCategory(Category category) throws CategoryException;

	public Category removeCategoryById(Integer categoryId) throws CategoryException;

	public List<Category> viewAllCategory() throws CategoryException;

	public Category viewCategoryById(Integer categoryId) throws CategoryException;

}
