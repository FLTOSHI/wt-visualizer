package prs.fltoshi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NavalVehicle extends Vehicle{
    private int mainCaliberCount; // кол-во пушек ГК
}
