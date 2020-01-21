package com.mo16.recipes4demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToMany(mappedBy = "categories")
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
