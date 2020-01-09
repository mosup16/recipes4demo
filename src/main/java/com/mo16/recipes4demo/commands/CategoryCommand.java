package com.mo16.recipes4demo.commands;


import com.mo16.recipes4demo.model.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCommand {
    private Long id;
    private String description;
//    private List<RecipeCommand> recipes;

//    public List<RecipeCommand> getRecipes() {
//        if (recipes == null) recipes = new ArrayList<>();
//        return recipes;
//    }

//    public void addRecipe(RecipeCommand recipe){
//        recipe.getCategories().add(this);
//        getRecipes().add(recipe);
//    }

    public Category toCategory() {
//        List<Recipe> recipesList = new ArrayList<>();
//        recipes.forEach(recipeCommand -> recipesList.add(recipeCommand.toRecipe()));

        Category category = new Category(id, description, null);
        return category;
    }

    public static CategoryCommand fromCategory(Category category) {
//        List<RecipeCommand> recipesCommandList = new ArrayList<>();
//        category.getRecipes().forEach(recipeCommand -> recipesCommandList.add(RecipeCommand.fromRecipe(recipeCommand)));

        CategoryCommand categoryCommand = new CategoryCommand(category.getId(), category.getDescription());
        return categoryCommand;
    }
}
