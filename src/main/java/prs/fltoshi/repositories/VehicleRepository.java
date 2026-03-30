package prs.fltoshi.repositories;

import prs.fltoshi.models.types.Vehicle;
import prs.fltoshi.services.VehicleSearchService.SearchCriteria;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    // Сохранение / обновление
    void save(Vehicle vehicle);
    
    // Поиск по внутреннему ID (например, "germ_pzkpfw_VI_ausf_E_tiger")
    Optional<Vehicle> findByIdentifier(String identifier);

    // Поиск по стране (СССР, США, Германия и т.д.)
    List<Vehicle> findByCountry(String country);
    
    // Поиск по типу (Aviation, Ground, Fleet)
    List<Vehicle> findByType(String type);

    // Поиск по рангу (I - VIII) — критично для прогрессии в WT
    List<Vehicle> findByRank(int rank);

    // Все записи для вывода
    List<Vehicle> findAll();

    // Универсальный поиск по критериям (возвращает DTO для UI)
    List<prs.fltoshi.models.dto.VehicleDTO> findByCriteria(SearchCriteria criteria);
}