package com.mo16.recipes4demo;

import com.mo16.recipes4demo.model.Category;
import com.mo16.recipes4demo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class BIRelationsITTest {

    
    @BeforeEach
    void setUp() {
    }

    @Test
    void BITest() {
        Recipe recipe = Recipe.builder().id("1").build();
        Category category = Category.builder().id("1").build();
        recipe.addCategory(category);
        category.addRecipe(recipe);
        assert category.getRecipes().size() == 2;
    }

    @Test
    void jFrameTest() {
        JFrame frame = new JFrame("good");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JButton("ggggggggggggg"));
        frame.setFocusable(true);
        frame.validate();
        frame.doLayout();
        frame.setEnabled(true);
    }

    @Test
    void test() {
        int i = 0;
        int[] ints = {0, 1, 2, 3};
        for (int n : ints) {
            System.out.println(ints[i++]);
        }
    }
}