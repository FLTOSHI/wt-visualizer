package prs.fltoshi.models.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
* Система вооружения техники
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeaponSystem {
    private List<Weapon> primaryWeapons;    // Основное вооружение
    private List<Weapon> secondaryWeapons;  // Вспомогательное

    // Авиация
    private boolean hasBallisticComputer;   // Имеет баллистический вычислитель?
    private int countermeasuresCount;       // Кол-во средств противодействия
}
