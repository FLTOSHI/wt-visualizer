package prs.fltoshi.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Информация об экипаже техники
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewService {
    private int crewTotalCount;             // Кол-во членов экипажа техники
    
    @JsonProperty("train1_cost")
    private int train1Cost;                 // Базовое обучение (серебро)
    
    @JsonProperty("train2_cost")
    private int train2Cost;                // Эксперты (серебро)
    
    @JsonProperty("train3_cost_gold")
    private int train3CostGold;         // Асы (золото)
    
    @JsonProperty("train3_cost_exp")
    private int train3CostExp;          // Асы (очки исследования)
}
