package prs.fltoshi.models.types;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
* Модель наземной техники
*/
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroundVehicle extends Vehicle {

    private double maxReverseSpeed; // Из блока Engine
}
