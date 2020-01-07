package com.mo16.recipes4demo.repositoris;

import com.mo16.recipes4demo.model.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes, Long> {
}
