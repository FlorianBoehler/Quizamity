package com.quizamity.service;

import com.quizamity.dao.CategoryDao;
import com.quizamity.model.Category;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class CategoryService {

    @Inject
    private CategoryDao categoryDao;

    public void createCategory(Category category) {
        categoryDao.create(category);
    }

    public Optional<Category> getCategory(UUID id) {
        return categoryDao.findById(id);
    }

    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    public boolean updateCategory(UUID id, Category updatedCategory) {
        return categoryDao.findById(id).map(category -> {
            category.setName(updatedCategory.getName());
            categoryDao.update(category);
            return true;
        }).orElse(false);
    }

    public boolean deleteCategory(UUID id) {
        return categoryDao.findById(id).map(category -> {
            categoryDao.delete(category);
            return true;
        }).orElse(false);
    }
}
