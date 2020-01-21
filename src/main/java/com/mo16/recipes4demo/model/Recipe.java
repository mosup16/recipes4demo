package com.mo16.recipes4demo.model;

import com.mo16.recipes4demo.model.enums.Difficulty;
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
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long prepTime;
    private Long cookTime;
    private Long servings;
    private String source;
    private String url;
    @Lob
    private String direction;
    @Lob
    private Byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<Ingredient> ingredients = new ArrayList<>();
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
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
        ingredient.setRecipe(this);
        getIngredients().add(ingredient);
    }

    public void addCategory(Category category) {
        category.getRecipes().add(this);
        getCategories().add(category);
    }
}
