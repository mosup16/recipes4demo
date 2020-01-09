package com.mo16.recipes4demo;

import com.mo16.recipes4demo.model.Category;
import com.mo16.recipes4demo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class BIRelationsITTest {

    
    @BeforeEach
    void setUp() {
    }

    @Test
    void BITest() {
        Recipe recipe = Recipe.builder().id(1L).build();
        Category category = Category.builder().id(1L).build();
        recipe.addCategory(category);
        category.addRecipe(recipe);
        assert category.getRecipes().size() == 2;
    }
}