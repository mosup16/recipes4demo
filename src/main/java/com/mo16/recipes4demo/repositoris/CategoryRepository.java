package com.mo16.recipes4demo.repositoris;

import com.mo16.recipes4demo.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByDescription(String description);
}
