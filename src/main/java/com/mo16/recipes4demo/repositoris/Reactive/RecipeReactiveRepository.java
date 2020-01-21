package com.mo16.recipes4demo.repositoris.Reactive;

import com.mo16.recipes4demo.model.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
    Mono<Recipe> findByDescription(String desc);
}
