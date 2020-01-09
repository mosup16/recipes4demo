package com.mo16.recipes4demo.commands;

import com.mo16.recipes4demo.model.Ingredient;
import com.mo16.recipes4demo.model.Recipe;
import com.mo16.recipes4demo.model.UnitOfMeasure;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    private RecipeCommand recipe;
    private UnitOfMeasureCommand unitOfMeasure;

    public Ingredient toIngredient() {
        Ingredient ingredient = new Ingredient(id, description, amount,
                (recipe == null ? null : recipe.toRecipe()), (unitOfMeasure == null ? null : unitOfMeasure.toUnitOfMeasure()));
        return ingredient;
    }

    public static IngredientCommand fromIngredient(Ingredient ingredient) {
        Recipe recipe = ingredient.getRecipe();
        UnitOfMeasure unitOfMeasure = ingredient.getUnitOfMeasure();
        RecipeCommand recipeCommand = recipe == null ? null : RecipeCommand.fromRecipe(recipe);
        UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasure == null ? null : UnitOfMeasureCommand.fromUnitOfMeasure(unitOfMeasure);

        IngredientCommand ingredientCommand = new IngredientCommand(ingredient.getId()
                , ingredient.getDescription(), ingredient.getAmount(), recipeCommand,unitOfMeasureCommand);
        return ingredientCommand;
    }
}
