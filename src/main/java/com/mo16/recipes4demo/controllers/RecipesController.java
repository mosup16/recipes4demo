package com.mo16.recipes4demo.controllers;

import com.mo16.recipes4demo.commands.RecipeCommand;
import com.mo16.recipes4demo.model.Recipe;
import com.mo16.recipes4demo.repositoris.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
    public String recipeInfo(@PathVariable("id") Long id, Model model) {
        log.info("Getting recipe page for recipe with id = " + id);
        model.addAttribute("recipe", recipeRepository.findById(id).get());
        return "recipes/show";
    }

    @GetMapping("recipes/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipes/recipeform";
    }

    @PostMapping("recipes")
    public String submitSave(@ModelAttribute RecipeCommand recipeCommand) {
        Recipe savedRecipe = recipeRepository.save(recipeCommand.toRecipe());
        return "redirect:/recipes/show/" + savedRecipe.getId();
    }

    @GetMapping("/recipes/update/{id}")
    public String UpdateRecipe(@PathVariable Long id, Model model) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        RecipeCommand recipeCommand = RecipeCommand.fromRecipe(recipe);
        model.addAttribute("recipe", recipeCommand);
        return "recipes/recipeform";
    }


    @GetMapping("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeRepository.deleteById(id);
        log.info("Delete recipe id = " + id);
        return "redirect:/";
    }


    @GetMapping("/recipes/{recipeId}/image")
    public String imageForm(@PathVariable Long recipeId, Model model) {
        model.addAttribute("recipe", recipeRepository.findById(recipeId).get());
        return "recipes/imageuploadform";
    }

    @PostMapping("/recipes/{recipeId}/image")
    public String saveImage(@PathVariable Long recipeId, @RequestParam("imagefile") MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();
        Byte[] image = new Byte[imageBytes.length];
        int i = 0;
        for (Byte b : imageBytes) {
            image[i++] = b;
        }
        Recipe recipe = recipeRepository.findById(recipeId).get();
        recipe.setImage(image);
        Recipe save = recipeRepository.save(recipe);
        return "redirect:/recipes/show/" + recipeId;
    }

    @GetMapping("/recipes/{recipeId}/recipeimage")
    public void renderImage(@PathVariable Long recipeId, HttpServletResponse response) throws IOException {
        Recipe recipe = recipeRepository.findById(recipeId).get();
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
}
