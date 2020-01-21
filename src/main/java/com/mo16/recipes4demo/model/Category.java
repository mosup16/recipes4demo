package com.mo16.recipes4demo.model;

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
public class Category {

    @Id
    private String id;
    private String description;
    @DBRef
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        if (recipes == null) recipes = new ArrayList<>();
        return recipes;
    }

    public void addRecipe(Recipe recipe){
        recipe.getCategories().add(this);
        getRecipes().add(recipe);
    }
}
