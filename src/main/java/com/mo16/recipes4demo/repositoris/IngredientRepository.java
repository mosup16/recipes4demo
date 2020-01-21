package com.mo16.recipes4demo.repositoris;

import com.mo16.recipes4demo.model.Ingredient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    @Transactional
    @Modifying
    @Query("delete from Ingredient i where i.id = :iid and i.recipe.id = :rid ")
    void deleteByIngredientIdAndRecipeId(@Param("iid") Long ingredientId, @Param("rid") Long recipeId);


    @Transactional
    @Query("from Ingredient i where i.id = :iid and i.recipe.id = :rid ")
    Optional<Ingredient> findByIngredientIdAndRecipeId(@Param("iid") Long ingredientId, @Param("rid") Long recipeId);

}
