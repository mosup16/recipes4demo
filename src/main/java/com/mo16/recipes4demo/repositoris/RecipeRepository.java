package com.mo16.recipes4demo.repositoris;

import com.mo16.recipes4demo.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    Optional<Recipe> findByDescription(String description);
}
