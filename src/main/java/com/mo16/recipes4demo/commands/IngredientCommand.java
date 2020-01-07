package com.mo16.recipes4demo.commands;

import com.mo16.recipes4demo.model.Ingredient;
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
}
