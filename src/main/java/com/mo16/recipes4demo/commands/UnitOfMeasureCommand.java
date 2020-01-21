package com.mo16.recipes4demo.commands;

import com.mo16.recipes4demo.model.UnitOfMeasure;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnitOfMeasureCommand {
    private String id;
    private String name;

    public UnitOfMeasure toUnitOfMeasure() {
        return new UnitOfMeasure(id, name);
    }

    public static UnitOfMeasureCommand fromUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null) return null;
        return new UnitOfMeasureCommand(unitOfMeasure.getId(), unitOfMeasure.getName());
    }
}
