package prs.fltoshi.services;

import prs.fltoshi.models.dto.VehicleDTO;
import prs.fltoshi.repositories.VehicleRepository;

import java.util.List;

public class VehicleSearchService {
    private final VehicleRepository repository;

    public VehicleSearchService(VehicleRepository repository) {
        this.repository = repository;
    }

    public record SearchCriteria(
        String query,
        String country,
        String type,
        Double minBr,
        Double maxBr,
        Integer rank
    ) {}
    
    public List<VehicleDTO> search(SearchCriteria criteria) {
        return repository.findByCriteria(criteria);
    }

    public String normalizeQuery(String raw) {
        if (raw == null || raw.isBlank()) return null;

        return raw.trim()
            .replace("T", "Т") // Латинская T на кириллическую Т
            .replace("A", "А")
            .replace("M", "М")
            .replace("-", "")  // Убираем дефис для поиска "Т34" как "Т-34"
            .toLowerCase();
    }
}
