package com.mo16.recipes4demo.commands;


import com.mo16.recipes4demo.model.Category;
import com.mo16.recipes4demo.model.Recipe;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCommand {
    private Long id;
    private String description;
    private List<RecipeCommand> recipes = new ArrayList<>();

    public Category toCategory() {
        List<Recipe> recipesList = new ArrayList<>();
        recipes.forEach(recipeCommand -> recipesList.add(recipeCommand.toRecipe()));

        Category category = new Category(id ,description ,recipesList);
        return category;
    }
}
