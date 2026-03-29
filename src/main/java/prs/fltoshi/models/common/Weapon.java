package prs.fltoshi.models.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Описывает модель вооружения
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weapon {
    private String name;                    // Название орудия
    private String type;                    // Тип (Cannon, Autocannon, ...)

    @JsonProperty("ammo_count")
    private int ammoCapacity;               // Общий боезапас

    @JsonProperty("reload_time")
    private double reloadTime;              // Время перезарядки (сек.)

    @JsonProperty("v_angles")
    private double[] verticalAngles;        // Углы верт. наводки

    @JsonProperty("h_speed")
    private double horizontalSpeed;         // Скорость гор. наводки

    private List<Ammunition> avaiableAmmo;  // Доступные снаряды для орудия.
}
