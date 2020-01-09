package com.mo16.recipes4demo.commands;

import com.mo16.recipes4demo.model.Ingredient;
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
    private UnitOfMeasureCommand unitOfMeasure;

    public Ingredient toIngredient() {
        UnitOfMeasure unitOfMeasure = this.unitOfMeasure == null ? null : this.unitOfMeasure.toUnitOfMeasure();
        Ingredient ingredient = new Ingredient(id, description, amount,
                null, unitOfMeasure);
        return ingredient;
    }

    public static IngredientCommand fromIngredient(Ingredient ingredient) {
        if (ingredient == null) return null;
        UnitOfMeasure unitOfMeasure = ingredient.getUnitOfMeasure();
        UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasure == null ? null : UnitOfMeasureCommand.fromUnitOfMeasure(unitOfMeasure);

        IngredientCommand ingredientCommand = new IngredientCommand(ingredient.getId()
                , ingredient.getDescription(), ingredient.getAmount(), unitOfMeasureCommand);
        return ingredientCommand;
    }
}
