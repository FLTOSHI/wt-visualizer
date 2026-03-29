package prs.fltoshi.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Собирает информацию о стоимости и времени ремонта техники.
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairStats {
    // Стоимость ремонта (база)
    @JsonProperty("repair_cost_arcade")
    private int costArcade;
    @JsonProperty("repair_cost_realistic")
    private int costRealistic;
    @JsonProperty("repair_cost_simulator")
    private int costSimulator;
    
    // Стоимость ремонта (топ)
    @JsonProperty("repair_cost_full_upgraded_arcade")
    private int fullUpgradeCostArcade;
    @JsonProperty("repair_cost_full_upgraded_realistic")
    private int fullUpgradeCostRealistic;
    @JsonProperty("repair_cost_full_upgraded_simulator")
    private int fullUpgradeCostSimulator;
    
    // Стоимость в минуту
    @JsonProperty("repair_cost_per_min_arcade")
    private int costPerMinuteArcade;
    @JsonProperty("repair_cost_per_min_realistic")
    private int costPerMinuteRealistic;
    @JsonProperty("repair_cost_per_min_simulator")
    private int costPerMinuteSimulator;

    // Время бесплатного ремонта (с. или мин.)
    @JsonProperty("repair_time_arcade")
    private double timeArcade;
    @JsonProperty("repair_time_realistic")
    private double timeRealistic;
    @JsonProperty("repair_time_simulator")
    private double timeSimulator;

    // Время ремонта без экипажа
    @JsonProperty("repair_time_no_crew_arcade")
    private double timeNoCrewArcade;
    @JsonProperty("repair_time_no_crew_realistic")
    private double timeNoCrewRealistic;
    @JsonProperty("repair_time_no_crew_simulator")
    private double timeNoCrewSimulator;
}
