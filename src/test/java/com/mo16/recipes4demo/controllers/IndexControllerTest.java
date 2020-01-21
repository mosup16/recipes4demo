package com.mo16.recipes4demo.controllers;

import com.mo16.recipes4demo.model.Recipe;
import com.mo16.recipes4demo.repositoris.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    IndexController indexController;
    @Mock
    Model model;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeRepository);
    }

    @Test
    void mockMvcTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void index() {
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe_1 = Recipe.builder().id("1").build();
        Recipe recipe_2 = Recipe.builder().id("2").build();

        recipes.add(recipe_1);
        recipes.add(recipe_2);

        ArgumentCaptor<List<Recipe>> argC= ArgumentCaptor.forClass(List.class);

        assert indexController.index(model).equals("index");
        verify(recipeRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), argC.capture());
    }
}