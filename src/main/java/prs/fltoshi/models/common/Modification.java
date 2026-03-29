package prs.fltoshi.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Представляет информацию о модификациях техники.
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown= true)  // Защита от новых полей
public class Modification {
    private String name;                    // Тех. имя
    private int tier;                       // ступень прокачки (1-4)

    @JsonProperty("repair_coeff")
    private double repairCoeff;             // Влияние на ремонт

    private int value;                      // Цена в СЛ

    @JsonProperty("req_exp")
    private int reqExp;                     // Требуемые ОИ
    
    @JsonProperty("ge_cost")
    private int geCost;                     // Цена в ЗО

    // Ссылка на прошлую модификацию
    @JsonProperty("required_modification")
    private String requiredModification;

    // Группа
    @JsonProperty("mod_class")
    private String modClass;                // "armor", 'firepower', ...

    private String icon;                    // Путь до иконки в ассетах

    // Доступ к категории
    public ModificationCategory getCategory(){
        return ModificationCategory.fromModClass(modClass);
    }

    // Проверки для фильтрации
    public boolean isWeaponry(){
        return getCategory() == ModificationCategory.WEAPONRY;
    }

    public boolean isMobility(){
        return getCategory() == ModificationCategory.MOBILITY;
    }

    public boolean isProtection(){
        return getCategory() == ModificationCategory.PROTECTION;
    }

    public boolean isUtility(){
        return getCategory() == ModificationCategory.UTILITY;
    }
}
