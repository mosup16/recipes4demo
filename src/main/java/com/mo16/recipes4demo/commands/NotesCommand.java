package com.mo16.recipes4demo.commands;

import com.mo16.recipes4demo.model.Notes;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesCommand {
    private String id;
    private String recipeNotes;

    public Notes toNotes() {
        return new Notes(id, null, recipeNotes);
    }

    public static NotesCommand fromNotes(Notes notes) {
        if (notes == null) return null;
        return new NotesCommand(notes.getId(), notes.getRecipeNotes());
    }
}
