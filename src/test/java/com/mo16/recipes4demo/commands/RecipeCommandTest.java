package com.mo16.recipes4demo.commands;

import com.mo16.recipes4demo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void toRecipe() {
        Long id = 1L;
        IngredientCommand ingredientCommand1 = IngredientCommand.builder().id(id).build();
        List<IngredientCommand> ingredientCommands = new ArrayList<>();
        ingredientCommands.add(ingredientCommand1);
        RecipeCommand recipeCommand = RecipeCommand.builder()
                .id(id)
                .description("recipe")
                .cookTime(10L)
                .ingredients(ingredientCommands)
                .build();
        Recipe recipe = recipeCommand.toRecipe();
        assert id.equals(recipe.getId());
        assert id.equals(recipe.getIngredients().get(0).getId());
    }
}