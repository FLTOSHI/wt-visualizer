package prs.fltoshi.models.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
* Модель самолета
*/
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AircraftVehicle extends Vehicle{

    // Аэродинамика
    private double length; // Длина самолета
    
    private double wingspan; // Размах крыла
    private double wingArea; // Площадь крыла
    
    private double maxTakeoffWeight; // Макс. взлётная масса
    private double maxAltitude; // Макс. высота

    private int turnRate; // Время виража

    private int runwayLengthRequired; // Длина разбега

    private int maxSpeedAtAltitude; // Макс. скорость на высоте ...

    private double climbRate;
    private boolean hasAfterburner;
}
