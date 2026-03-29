package prs.fltoshi.models;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroundVehicle extends Vehicle {

    // Броня
    private List<Integer> hullArmor; // [лоб, борт, корма]
    private List<Integer> turretArmor; // [лоб, борт, корма]

    private double maxReverseSpeed; // Из блока Engine
    private String requiredVehicle; // требуемая машина
}
