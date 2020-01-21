package com.mo16.recipes4demo.controllers;

import com.mo16.recipes4demo.model.Recipe;
import com.mo16.recipes4demo.repositoris.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class RecipesControllerTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipesController controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void recipeInfo() throws Exception {
        Recipe recipe = Recipe.builder().id("2").build();
        Mockito.when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipe));
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
        mvc.perform(get("/recipes/show/2"))
                .andExpect(status().isOk()).andExpect(view().name("recipes/show"));
    }
}