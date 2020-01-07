package com.mo16.recipes4demo.repositoris;

import com.mo16.recipes4demo.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByNameCup() {
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findByName("Cup").get();
        assert "Cup".equals(unitOfMeasure.getName());
    }

    @Test
    void findByNameSpoon() {
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findByName("Spoon").get();
        assert "Spoon".equals(unitOfMeasure.getName());
    }
}