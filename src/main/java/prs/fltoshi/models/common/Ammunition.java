package prs.fltoshi.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Описывает тип снаряда / пули
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ammunition {
    private String name;
    private String type;

    @JsonProperty("speed")
    private double initialSpeed;            // Начальная скорость полета (м/с)

    private int penetration;                // Пробитие под нулем градусов на 100м (мм)

    @JsonProperty("explosive_mass")
    private double explosiveMass;           // Масса взрывчатки (кг в тротиле)

    private double mass;                    // Масса снаряда (кг)
}
