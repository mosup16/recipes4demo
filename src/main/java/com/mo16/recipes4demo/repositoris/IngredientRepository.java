package com.mo16.recipes4demo.repositoris;

import com.mo16.recipes4demo.model.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {

    @Transactional
//    @Modifying
    @Query("delete from Ingredient i where i.id = :iid and i.recipe.id = :rid ")
    void deleteByIngredientIdAndRecipeId(@Param("iid") String ingredientId, @Param("rid") String recipeId);


    @Transactional
    @Query("from Ingredient i where i.id = :iid and i.recipe.id = :rid ")
    Optional<Ingredient> findByIngredientIdAndRecipeId(@Param("iid") String ingredientId, @Param("rid") String recipeId);

    

}
