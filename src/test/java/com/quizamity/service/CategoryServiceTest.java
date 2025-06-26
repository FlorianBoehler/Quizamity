package com.quizamity.service;

import com.quizamity.dao.CategoryDao;
import com.quizamity.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryService categoryService;

    private UUID categoryId;
    private Category category;

    @BeforeEach
    void setUp() {
        categoryId = UUID.randomUUID();
        category = new Category();
        category.setId(categoryId);
        category.setName("Science");
    }

    @Test
    void createCategory_shouldCallDaoCreate() {
        // Act
        categoryService.createCategory(category);

        // Assert
        verify(categoryDao, times(1)).create(category);
    }

    @Test
    void getCategory_shouldReturnCategory_whenFound() {
        // Arrange
        when(categoryDao.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        Optional<Category> result = categoryService.getCategory(categoryId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Science", result.get().getName());
    }

    @Test
    void getCategory_shouldReturnEmpty_whenNotFound() {
        // Arrange
        when(categoryDao.findById(categoryId)).thenReturn(Optional.empty());

        // Act
        Optional<Category> result = categoryService.getCategory(categoryId);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllCategories_shouldReturnListOfCategories() {
        // Arrange
        List<Category> categories = List.of(category);
        when(categoryDao.findAll()).thenReturn(categories);

        // Act
        List<Category> result = categoryService.getAllCategories();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Science", result.get(0).getName());
    }

    @Test
    void findByName_shouldReturnCategory_whenFound() {
        // Arrange
        when(categoryDao.findByName("Science")).thenReturn(Optional.of(category));

        // Act
        Optional<Category> result = categoryService.findByName("Science");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Science", result.get().getName());
    }

    @Test
    void findByName_shouldReturnEmpty_whenNotFound() {
        // Arrange
        when(categoryDao.findByName("Math")).thenReturn(Optional.empty());

        // Act
        Optional<Category> result = categoryService.findByName("Math");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void updateCategory_shouldUpdateAndReturnTrue_whenCategoryExists() {
        // Arrange
        Category updated = new Category();
        updated.setName("Math");

        when(categoryDao.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        boolean result = categoryService.updateCategory(categoryId, updated);

        // Assert
        assertTrue(result);
        assertEquals("Math", category.getName());
        verify(categoryDao).update(category);
    }

    @Test
    void updateCategory_shouldReturnFalse_whenCategoryNotFound() {
        // Arrange
        Category updated = new Category();
        updated.setName("Math");

        when(categoryDao.findById(categoryId)).thenReturn(Optional.empty());

        // Act
        boolean result = categoryService.updateCategory(categoryId, updated);

        // Assert
        assertFalse(result);
        verify(categoryDao, never()).update(any());
    }

    @Test
    void deleteCategory_shouldDeleteAndReturnTrue_whenCategoryExists() {
        // Arrange
        when(categoryDao.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        boolean result = categoryService.deleteCategory(categoryId);

        // Assert
        assertTrue(result);
        verify(categoryDao).delete(category);
    }

    @Test
    void deleteCategory_shouldReturnFalse_whenCategoryNotFound() {
        // Arrange
        when(categoryDao.findById(categoryId)).thenReturn(Optional.empty());

        // Act
        boolean result = categoryService.deleteCategory(categoryId);

        // Assert
        assertFalse(result);
        verify(categoryDao, never()).delete(any());
    }
}
