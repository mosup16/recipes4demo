package com.mo16.recipes4demo.controllers;

import com.mo16.recipes4demo.commands.RecipeCommand;
import com.mo16.recipes4demo.exceptions.NotFoundException;
import com.mo16.recipes4demo.model.Recipe;
import com.mo16.recipes4demo.repositoris.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;


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

    @GetMapping("/recipes/show/{id}")
    public String recipeInfo(@PathVariable("id") String id, Model model) {
        log.info("Getting recipe page for recipe with id = " + id);
        model.addAttribute("recipe", findRecipeById(id));
        return "recipes/show";
    }

    @GetMapping("recipes/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipes/recipeform";
    }

    @PostMapping("recipes")
    public String submitSave(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult result) {
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> log.error(error.getField() + " : " + error.getDefaultMessage()));
            return "recipes/recipeform";
        }
        Recipe savedRecipe = recipeRepository.save(recipeCommand.toRecipe());
        return "redirect:/recipes/show/" + savedRecipe.getId();
    }

    @GetMapping("/recipes/update/{id}")
    public String UpdateRecipe(@PathVariable String id, Model model) {
        Recipe recipe = findRecipeById(id);
        RecipeCommand recipeCommand = RecipeCommand.fromRecipe(recipe);
        model.addAttribute("recipe", recipeCommand);
        return "recipes/recipeform";
    }


    @GetMapping("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable String id) {
        recipeRepository.deleteById(id);
        log.info("Delete recipe id = " + id);
        return "redirect:/";
    }


    @GetMapping("/recipes/{recipeId}/image")
    public String imageForm(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", findRecipeById(recipeId));
        return "recipes/imageuploadform";
    }

    @PostMapping("/recipes/{recipeId}/image")
    public String saveImage(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();
        Byte[] image = new Byte[imageBytes.length];
        int i = 0;
        for (Byte b : imageBytes) {
            image[i++] = b;
        }
        Recipe recipe = findRecipeById(recipeId);
        ;
        recipe.setImage(image);
        Recipe save = recipeRepository.save(recipe);
        return "redirect:/recipes/show/" + recipeId;
    }

    @GetMapping("/recipes/{recipeId}/recipeimage")
    public void renderImage(@PathVariable String recipeId, HttpServletResponse response) throws IOException {
        Recipe recipe = findRecipeById(recipeId);
        response.setContentType("image");
        Byte[] byteImage = recipe.getImage();
        if (byteImage != null) {
            byte[] image = new byte[byteImage.length];
            int i = 0;
            for (byte b : byteImage) {
                image[i++] = b;
            }
            response.getOutputStream().write(image);
            response.flushBuffer();
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String notFoundErrorHandler(Exception e, Model model) {
        model.addAttribute("exception", e);
        log.error("notFoundError : " + e.getMessage());
        return "404error";
    }

    private Recipe findRecipeById(String recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent())
            throw new NotFoundException("Could not find Recipe with the following id = " + recipeId);
        return recipeOptional.get();
    }
}
