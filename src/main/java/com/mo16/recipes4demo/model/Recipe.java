package com.mo16.recipes4demo.model;

import com.mo16.recipes4demo.model.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Recipe {

    @Id
    private String id;
    private String description;
    private Long prepTime;
    private Long cookTime;
    private Long servings;
    private String source;
    private String url;
    private String direction;
    private Byte[] image;
    private Notes notes;
    private List<Ingredient> ingredients;
    private Difficulty difficulty;
    @DBRef
    private List<Category> categories;

    public List<Ingredient> getIngredients() {
        if (ingredients == null) ingredients = new ArrayList<>();
        return ingredients;
    }

    public List<Category> getCategories() {
        if (categories == null) categories = new ArrayList<>();
        return categories;
    }

    public void addIngredient(Ingredient ingredient) {
//        ingredient.setRecipe(this);
        getIngredients().add(ingredient);
    }

    public void addCategory(Category category) {
//        category.getRecipes().add(this);
        getCategories().add(category);
    }
}
