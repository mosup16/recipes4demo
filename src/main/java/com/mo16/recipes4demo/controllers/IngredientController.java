package com.mo16.recipes4demo.controllers;

import com.mo16.recipes4demo.commands.IngredientCommand;
import com.mo16.recipes4demo.model.Ingredient;
import com.mo16.recipes4demo.repositoris.IngredientRepository;
import com.mo16.recipes4demo.repositoris.RecipeRepository;
import com.mo16.recipes4demo.repositoris.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;

@Slf4j
@Controller
public class IngredientController implements Serializable {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public IngredientController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @GetMapping("recipes/{recipeId}/ingredients")
    public String IngredientList(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeRepository.findById(recipeId).orElse(null));
        log.info("get list off ingredients for recipe id = " + recipeId);
        return "recipes/Ingredients/list";
    }

    @GetMapping("recipes/{recipeId}/ingredients/show/{ingredientId}")
    public String showIngredient(@PathVariable String ingredientId, @PathVariable String recipeId, Model model) {
        Ingredient ingredient1 = ingredientRepository.findByIngredientIdAndRecipeId(ingredientId, recipeId).orElse(null);
        model.addAttribute("ingredient", ingredient1);
        return "recipes/ingredients/show";
    }

    @GetMapping("recipes/{recipeId}/ingredients/update/{ingredientId}")
    public String updateIngredient(@PathVariable String ingredientId, @PathVariable String recipeId, Model model) {
        Ingredient ingredient1 = ingredientRepository.findByIngredientIdAndRecipeId(ingredientId, recipeId).orElse(null);
        model.addAttribute("ingredient", IngredientCommand.fromIngredient(ingredient1));
        model.addAttribute("uomList", unitOfMeasureRepository.findAll());
        return "recipes/ingredients/ingredientform";
    }

    @GetMapping("recipes/{recipeId}/ingredients/delete/{ingredientId}")
    public String deleteIngredient(@PathVariable String ingredientId, @PathVariable String recipeId) {
        ingredientRepository.deleteByIngredientIdAndRecipeId(ingredientId, recipeId);
        return "redirect:/recipes/" + recipeId + "/ingredients/";
    }

    @GetMapping("/recipes/{recipeId}/ingredients/new")
    public String newIngredient(@PathVariable("recipeId") String recipeId, Model model) {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", unitOfMeasureRepository.findAll());
        return "recipes/ingredients/ingredientform";
    }

    @PostMapping("recipes/{recipeId}/ingredients/")
    public String saveOrUpdate(@PathVariable String recipeId, @ModelAttribute IngredientCommand ingredientCommand) {
        ingredientCommand.setRecipeId(recipeId);
        Ingredient ingredient = ingredientCommand.toIngredient();
        ingredient.setRecipe(recipeRepository.findById(recipeId).get());

        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return "redirect:/recipes/" + savedIngredient.getRecipe().getId() + "/ingredients/";
    }

}
