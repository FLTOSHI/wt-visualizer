package prs.fltoshi.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Собирает информацию о модификаторах фарма СЛ и ОИ техники.
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EconomyRewards {

    @JsonProperty("sl_mul_arcade")
    private double slMulArcade;             // Модификатор СЛ в АБ

    @JsonProperty("sl_mul_realistic")
    private double slMulRealistic;          // Модификатор СЛ в РБ боях

    @JsonProperty("sl_mul_simulator")
    private double slMulSimulator;          // Модификатор СЛ в СБ

    @JsonProperty("exp_mul")
    private double expMul;                  // Один на все режимы.
}
