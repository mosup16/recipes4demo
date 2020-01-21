package com.mo16.recipes4demo.repositoris;

import com.mo16.recipes4demo.model.UnitOfMeasure;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends MongoRepository<UnitOfMeasure, String> {
    Optional<UnitOfMeasure> findByName(String name);
}
