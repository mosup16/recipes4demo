package com.mo16.recipes4demo.controllers;

import com.mo16.recipes4demo.repositoris.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
public class RecipesController {
    private RecipeRepository recipeRepository;

    public RecipesController() {
    }

    @Autowired
    public RecipesController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping("/recipes/show/{id}")
    public String recipeInfo(@PathVariable("id") Long id , Model model){
        log.info("Getting recipe page for recipe with id = " + id);
        model.addAttribute("recipe" , recipeRepository.findById(id).get());
        return "recipes/show";
    }
}
