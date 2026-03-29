package prs.fltoshi.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Описывает электронику техники
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorSuite {
    // ПНВ, ТПВ
    private boolean hasNvd;                 // Имеет ПНВ?
    private boolean hasThermal;             // Имеет ТПВ?
    private String thermalGen;              // Поколение или разрешение

    // Авиационка
    private boolean hasRadar;               // Имеет РЛС?
    private boolean hasIrtst;               // Имеет ОЛС?

    // Системы наведения и защиты
    @JsonProperty("laser_rangefinder")
    private boolean hasLaserRangefinger;    // Имеет лазерный дальномер?

    @JsonProperty("lws_ircm")
    private boolean hasLws;                 // Имеет СПО?

    @JsonProperty("ballistic_computer")
    private boolean hasBallisticComputer;   // Имеет СУО?
}
