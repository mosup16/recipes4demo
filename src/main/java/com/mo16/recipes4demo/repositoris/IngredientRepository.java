package com.mo16.recipes4demo.repositoris;

import com.mo16.recipes4demo.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
