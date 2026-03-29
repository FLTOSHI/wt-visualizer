package prs.fltoshi.models.common;

import lombok.Getter;

/*
* Категория модификаций
*/
@Getter
public enum ModificationCategory {
    PROTECTION("Защита"), 
    MOBILITY("Подвижность"), 
    WEAPONRY("Вооружение"), 
    UTILITY("Разное"), 
    UNKNOWN("Неизвестно");

    private final String displayName;

    ModificationCategory(String displayName) {
        this.displayName = displayName;
    }

    public static ModificationCategory fromModClass(String modClass) {
        if (modClass == null) return UNKNOWN;
        
        return switch (modClass.toLowerCase()) {
            case "armor", "protection", "unsinkability" -> PROTECTION;
            case "lth", "mobility", "seakeeping" -> MOBILITY;
            case "weapon", "firepower" -> WEAPONRY;
            default -> UTILITY;
        };
    }
}