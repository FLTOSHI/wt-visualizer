package prs.fltoshi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AircraftVehicle extends Vehicle{
    private double wingspan;
    private double maxAltitude;
    private double climbRate;
    private boolean hasAfterburner;
}
