package com.mo16.recipes4demo.repositoris;

import com.mo16.recipes4demo.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<Notes, String> {
}
