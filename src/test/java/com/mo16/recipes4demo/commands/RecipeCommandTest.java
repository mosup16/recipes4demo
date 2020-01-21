package com.mo16.recipes4demo.commands;

import com.mo16.recipes4demo.model.Ingredient;
import com.mo16.recipes4demo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RecipeCommandTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void toRecipe() {
        String id = "1";
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

    @Test
    void fromRecipe(){
        String id = "1";
        Ingredient ingredient1 = Ingredient.builder().id(id).build();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);

        Recipe recipe = Recipe.builder()
                .id(id)
                .description("recipe")
                .cookTime(10L)
                .build();
        recipe.addIngredient(ingredient1);
        RecipeCommand recipeCommand = RecipeCommand.fromRecipe(recipe);
        assert id.equals(recipeCommand.getId());
        assert id.equals(recipeCommand.getIngredients().get(0).getId());
    }
}