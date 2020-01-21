package com.mo16.recipes4demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

//import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notes {
    @Id
    private String id;
    @DBRef
    private Recipe recipe;
    private String recipeNotes;

}
