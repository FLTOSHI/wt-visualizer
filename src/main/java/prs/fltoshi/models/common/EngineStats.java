package prs.fltoshi.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Собирает информацию о двигателе техники.
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineStats {
    // TODO: найти способ доставать информацию о двигателе(-ях) 


    // Мощность (HP)
    @JsonProperty("horse_power_ab")
    private int horsePowerAb;

    @JsonProperty("horse_power_rb_sb")
    private int horsePowerRbSb;

    // Обороты/мин (RPM)
    @JsonProperty("max_rpm")
    private int maxRpm;

    // Скорость вперёд (км/ч или узлы)
    @JsonProperty("max_speed_ab")
    private double maxSpeedAb;

    @JsonProperty("max_speed_rb_sb")
    private double maxSpeedRbSb;

    // Скорость назад (км/ч или узлы)
    @JsonProperty("max_reverse_speed_ab")
    private double maxReverseSpeedAb;

    @JsonProperty("max_reverse_speed_rb_sb")
    private double maxReverseSpeedRbSb;

}
