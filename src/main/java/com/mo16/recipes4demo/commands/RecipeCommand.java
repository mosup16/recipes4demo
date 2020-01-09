package com.mo16.recipes4demo.commands;

import com.mo16.recipes4demo.model.Category;
import com.mo16.recipes4demo.model.Ingredient;
import com.mo16.recipes4demo.model.Recipe;
import com.mo16.recipes4demo.model.enums.Difficulty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeCommand {
    private Long id;
    private String description;
    private Long prepTime;
    private Long cookTime;
    private Long servings;
    private String source;
    private String url;
    private String direction;
    private Byte[] image;
    private NotesCommand notes;
    private List<IngredientCommand> ingredients;
    private Difficulty difficulty;
    private List<CategoryCommand> categories;

    public List<IngredientCommand> getIngredients(){
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        return ingredients;
    }

    public List<CategoryCommand> getCategories(){
        if (categories == null) {
            categories = new ArrayList<>();
        }
        return categories;
    }

    public void addIngredient(IngredientCommand ingredient) {
//        ingredient.setRecipe(this);
        getIngredients().add(ingredient);
    }

    public void addCategory(CategoryCommand category) {
//        category.getRecipes().add(this);
        getCategories().add(category);
    }

    public Recipe toRecipe() {
        List<Ingredient> ingredientsList = new ArrayList<>();
        getIngredients().forEach(ingredientCommand -> {
            ingredientsList.add(ingredientCommand.toIngredient());
        });
        List<Category> categoriesList = new ArrayList<>();
        getCategories().forEach(categoryCommand -> {
            categoriesList.add(categoryCommand.toCategory());
        });

        Recipe recipe = new Recipe(id, description, prepTime, cookTime, servings, source, url, direction, image,
                (this.notes == null ? null : notes.toNotes()), ingredientsList, difficulty, categoriesList);
        return recipe;
    }

    public static RecipeCommand fromRecipe(Recipe recipe){
        List<IngredientCommand> ingredientsList = new ArrayList<>();
        recipe.getIngredients().forEach(ingredient -> {
            ingredientsList.add(IngredientCommand.fromIngredient(ingredient));
        });
        List<CategoryCommand> categoriesList = new ArrayList<>();
        recipe.getCategories().forEach(category -> {
            categoriesList.add(CategoryCommand.fromCategory(category));
        });

        RecipeCommand recipeCommand = new RecipeCommand(recipe.getId(), recipe.getDescription()
                , recipe.getPrepTime(), recipe.getCookTime(), recipe.getServings(), recipe.getSource()
                , recipe.getUrl(), recipe.getDirection(), recipe.getImage(),
                (recipe.getNotes() == null ? null : NotesCommand.fromNotes(recipe.getNotes()))
                , ingredientsList, recipe.getDifficulty(), categoriesList);
        return recipeCommand;
    }

}
