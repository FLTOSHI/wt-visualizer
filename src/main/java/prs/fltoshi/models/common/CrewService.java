package prs.fltoshi.models.common;

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
public class CrewService {
    private int crewTotalCount;             // Кол-во членов экипажа техники
    
    @JsonProperty("train1_cost")
    private int trainFirstCost;             // Базовое обучение (серебро)
    
    @JsonProperty("train2_cost")
    private int trainSecondCost;            // Эксперты (серебро)
    
    @JsonProperty("train3_cost_gold")
    private int trainThirdCostGold;         // Асы (золото)
    
    @JsonProperty("train3_cost_exp")
    private int trainThirdCostExp;          // Асы (очки исследования)
}
