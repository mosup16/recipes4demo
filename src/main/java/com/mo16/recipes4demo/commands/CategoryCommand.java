package com.mo16.recipes4demo.commands;


import com.mo16.recipes4demo.model.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCommand {
    private String id;
    private String description;

    public Category toCategory() {
        return new Category(id, description, null);
    }

    public static CategoryCommand fromCategory(Category category) {
        if (category == null) return null;
        return new CategoryCommand(category.getId(), category.getDescription());
    }
}
