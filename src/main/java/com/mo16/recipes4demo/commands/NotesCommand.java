package com.mo16.recipes4demo.commands;

import com.mo16.recipes4demo.model.Notes;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesCommand {
    private Long id;
    private RecipeCommand recipe;
    private String recipeNotes;

    public Notes toNotes() {
        Notes notes = new Notes(id , (recipe == null ? null: recipe.toRecipe()),recipeNotes);
        return notes;
    }
}
