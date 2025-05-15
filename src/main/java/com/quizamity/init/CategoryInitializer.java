package com.quizamity.init;

import com.quizamity.model.Category;
import com.quizamity.service.CategoryService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.List;

@Startup
@Singleton
public class CategoryInitializer {

    @Inject
    private CategoryService categoryService;

    @PostConstruct
    public void init() {
        List<String> standardCategories = List.of("IT-Projektmanagement", "Qualitätssicherung im Softwareprozess", "IT-Recht");

        for (String categoryName : standardCategories) {
            categoryService.findByName(categoryName).orElseGet(() -> {
                Category c = new Category(categoryName);
                categoryService.createCategory(c);
                return c;
            });
        }
    }
}
