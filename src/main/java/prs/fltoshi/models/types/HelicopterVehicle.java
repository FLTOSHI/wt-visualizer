package prs.fltoshi.models.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
* Модель вертолета
*/
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HelicopterVehicle extends Vehicle {
    private double maxSpeedAtGround;
}
