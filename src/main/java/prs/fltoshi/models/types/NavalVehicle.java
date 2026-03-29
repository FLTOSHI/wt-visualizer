package prs.fltoshi.models.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
* Модель корабля / катера
*/
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NavalVehicle extends Vehicle{
    private int mainCaliberCount; // кол-во пушек ГК
}
