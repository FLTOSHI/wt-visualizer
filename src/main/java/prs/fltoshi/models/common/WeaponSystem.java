package prs.fltoshi.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
* Система вооружения техники
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeaponSystem {
    @JsonProperty("weapons")
    @Builder.Default
    private List<Weapon> primaryWeapons = new ArrayList<>(); // Основное вооружение (пушки)

    @JsonProperty("presets")
    @Builder.Default
    private List<WeaponPreset> weaponPresets = new ArrayList<>(); // Пресеты (подвесы)

    @JsonProperty("has_customizable_weapons")
    private boolean hasCustomizableWeapons; // Есть ли конструктор подвесов?

    // Авиация
    private boolean hasBallisticComputer;   // Имеет баллистический вычислитель?
    private int countermeasuresCount;       // Кол-во средств противодействия
}
