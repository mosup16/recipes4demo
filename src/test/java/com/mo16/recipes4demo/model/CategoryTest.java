package com.mo16.recipes4demo.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class CategoryTest {

    Category category;

    @BeforeEach
    public void Setup(){
        category = new Category();
        log.info("setup CategoryTest");
    }

    @Test
    void getId() {
        long id = 22L;
        category.setId(id);
        assert category.getId() == id;
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}