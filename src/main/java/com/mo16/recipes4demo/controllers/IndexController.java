package com.mo16.recipes4demo.controllers;

import com.mo16.recipes4demo.repositoris.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    private RecipeRepository recipeRepository;

    @Autowired
    public IndexController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping({"", "/", "index", "index.html"})
    public String index(Model model) {
        log.info("Getting the Index page");
        model.addAttribute("recipes" ,recipeRepository.findAll());
        return "index";
    }
}
