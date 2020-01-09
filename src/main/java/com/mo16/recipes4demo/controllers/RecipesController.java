package com.mo16.recipes4demo.controllers;

import com.mo16.recipes4demo.commands.RecipeCommand;
import com.mo16.recipes4demo.model.Recipe;
import com.mo16.recipes4demo.repositoris.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @RequestMapping("recipes/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe" ,new RecipeCommand());
        return "recipes/recipeform";
    }

    @PostMapping
    @RequestMapping("recipes")
    public String submitSave(@ModelAttribute RecipeCommand recipeCommand){
        Recipe savedRecipe = recipeRepository.save(recipeCommand.toRecipe());
        return "redirect:/recipes/show/" + savedRecipe.getId();
    }

    @RequestMapping("/recipes/update/{id}")
    public String UpdateRecipe(@PathVariable String id ,Model model){
        Recipe recipe = recipeRepository.findById(Long.valueOf(id)).orElse(null);
        RecipeCommand recipeCommand = RecipeCommand.fromRecipe(recipe);
        model.addAttribute("recipe" , recipeCommand);
        return "recipes/recipeform";
    }


    @RequestMapping("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable String id) {
        recipeRepository.deleteById(Long.valueOf(id));
        log.info("Delete recipe id = " + id);
        return "redirect:/";
    }
}
