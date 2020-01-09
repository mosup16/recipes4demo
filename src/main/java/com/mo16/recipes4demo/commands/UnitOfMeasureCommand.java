package com.mo16.recipes4demo.commands;

import com.mo16.recipes4demo.model.UnitOfMeasure;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnitOfMeasureCommand {
    private Long id;
    private String name;

    public UnitOfMeasure toUnitOfMeasure() {
        UnitOfMeasure uom = new UnitOfMeasure(id ,name);
        return uom;
    }

    public static UnitOfMeasureCommand fromUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand(unitOfMeasure.getId(), unitOfMeasure.getName());
        return unitOfMeasureCommand;
    }
}
